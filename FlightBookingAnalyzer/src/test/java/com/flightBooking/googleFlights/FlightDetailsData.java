package com.flightBooking.googleFlights;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FlightDetailsData {

	public static void extractFlightDetails(WebDriver driver, WebDriverWait wait,String departureCity, String destinationCity,String departureDate,String returnDate) {
		List<Integer> prices = new ArrayList<>();
		List<Integer> durations = new ArrayList<>();
		List<String> flightNames = new ArrayList<>();

		String originalWindow = driver.getWindowHandle();
		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
		if (windowHandles.size() > 1) {
			String secondWindowHandle = windowHandles.get(1);
			driver.switchTo().window(secondWindowHandle);
		}
		
		WebElement departureInput = driver.findElement(By.xpath(XPathConstants.Departure));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", departureInput);
		departureInput.clear();
		
		departureInput.sendKeys(departureDate,Keys.ENTER);
		WebElement returnInput = driver.findElement(By.xpath(XPathConstants.Return));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", returnInput);
		returnInput.clear();
		
		returnInput.sendKeys(returnDate,Keys.ENTER);
		WebElement countoful = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathConstants.FLIGHT_LIST_UL)));
		FlightBookingUtils.pauseExecution(3000);
		List<WebElement> countofli = countoful.findElements(By.xpath(XPathConstants.FLIGHT_LIST_ITEM));
		int totalItemCountFirst = countofli.size();

		for (int i = 0; i < totalItemCountFirst; i++) {
			WebElement ulElement = countofli.get(i);

			try {
				WebElement departureTime = ulElement.findElement(By.xpath(XPathConstants.DEPARTURE_TIME));
				String departure = departureTime.getText();

				WebElement arrivalTime = ulElement.findElement(By.xpath(XPathConstants.ARRIVAL_TIME));
				String arrival = arrivalTime.getText();

				WebElement totalTimeElement = ulElement.findElement(By.xpath(XPathConstants.TOTAL_TIME));
				String totalTime = totalTimeElement.getText();
				
				int durationInMinutes = FlightBookingUtils.parseDuration(totalTime);
				durations.add(durationInMinutes);

				WebElement fromAirport = ulElement.findElement(By.xpath(XPathConstants.FROM_AIRPORT));
				WebElement toAirport = ulElement.findElement(By.xpath(XPathConstants.TO_AIRPORT));
				String fromAirportCode = fromAirport.getText();
				String toAirportCode = toAirport.getText();

				WebElement priceElement = ulElement.findElement(By.xpath(XPathConstants.PRICE));
				String priceText = priceElement.getText();
				int price = FlightBookingUtils.parsePrice(priceText);
				prices.add(price);

				WebElement flightNameElement = ulElement.findElement(By.xpath(XPathConstants.FLIGHT_NAME));
				String flightName = flightNameElement.getText();
				flightNames.add(flightName);


				WebElement roundTripElement = ulElement.findElement(By.xpath(XPathConstants.ROUND_TRIP));
				String roundTripText = roundTripElement.getText();
			} catch (NoSuchElementException e) {
				System.out.println("Element not found: " + e.getMessage());
			}
		}
		WebElement initialUl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathConstants.SECOND_FLIGHTS)));
		List<WebElement> initialLiList = initialUl.findElements(By.xpath(XPathConstants.FLIGHT_LIST_ITEM));
		WebElement clickButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathConstants.MORE_FLIGHTS)));
		clickButton.click();
		FlightBookingUtils.pauseExecution(3000);
		WebElement updatedUl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPathConstants.SECOND_FLIGHTS)));
		List<WebElement> updatedLiList = updatedUl.findElements(By.xpath(XPathConstants.FLIGHT_LIST_ITEM));
		int totalItemCount = updatedLiList.size();
		for (int i = 0; i < totalItemCount -1; i++) {
			WebElement liElement = updatedLiList.get(i);
			try {
				WebElement departureTime = liElement.findElement(By.xpath(XPathConstants.DEPARTURE_TIME));
				String departure = departureTime.getText();

				WebElement arrivalTime = liElement.findElement(By.xpath(XPathConstants.ARRIVAL_TIME));
				String arrival = arrivalTime.getText();

				WebElement totalTimeElement = liElement.findElement(By.xpath(XPathConstants.TOTAL_TIME));
				String totalTime = totalTimeElement.getText();
				int durationInMinutes = FlightBookingUtils.parseDuration(totalTime);
				durations.add(durationInMinutes);

				WebElement fromAirport = liElement.findElement(By.xpath(XPathConstants.FROM_AIRPORT));
				WebElement toAirport = liElement.findElement(By.xpath(XPathConstants.TO_AIRPORT));
				String fromAirportCode = fromAirport.getText();
				String toAirportCode = toAirport.getText();

				WebElement priceElement = liElement.findElement(By.xpath(XPathConstants.PRICE));
				String priceText = priceElement.getText();
				int price = FlightBookingUtils.parsePrice(priceText);
				prices.add(price);

				WebElement flightNameElement = liElement.findElement(By.xpath(XPathConstants.FLIGHT_NAME));
				String flightName = flightNameElement.getText();
				flightNames.add(flightName);

				WebElement roundTripElement = liElement.findElement(By.xpath(XPathConstants.ROUND_TRIP));
				String roundTripText = roundTripElement.getText();

				//				System.out.println("Departure Time: " + departure + " Arrival Time: " + arrival);
				//				System.out.println("Total Duration Time: " + totalTime);
				//				System.out.println("From: " + fromAirportCode + "  To: " + toAirportCode);
				//				System.out.println("Price: " + priceText + " for: " + roundTripText);

			} catch (NoSuchElementException e) {
				System.out.println("Element not found: " + e.getMessage());
			}

		}

		printFlightDetails(prices, durations, flightNames, departureCity, destinationCity,departureDate,returnDate);
	}	

	private static void printFlightDetails(List<Integer> prices, List<Integer> durations, List<String> flightNames,String departureCity, String destinationCity,String departureDate,String returnDate) {
		if (!prices.isEmpty() && !durations.isEmpty() && !flightNames.isEmpty()) {
		
			int cheapestPrice = Collections.min(prices);
			int cheapestIndex = prices.indexOf(cheapestPrice);
			int mostExpensivePrice = Collections.max(prices);
			int mostExpensiveIndex = prices.indexOf(mostExpensivePrice);

			int shortestDuration = Collections.min(durations);
			int shortestIndex = durations.indexOf(shortestDuration);
			int longestDuration = Collections.max(durations);
			int longestIndex = durations.indexOf(longestDuration);

			System.out.println("****************************");
			System.out.println("");
			System.out.println("From: " + departureCity + " To: " + destinationCity);
			System.out.println("From: " + departureDate + " To: " + returnDate);
			System.out.println("");
			System.out.println("****************************");

			System.out.println("");
			System.out.println("**Cheapest price flight details are: **");
			System.out.println("              Price: $" + cheapestPrice);
			System.out.println("              Duration: " + FlightBookingUtils.formatDuration(durations.get(cheapestIndex)));
			System.out.println("              Flight Name: " + flightNames.get(cheapestIndex));

			System.out.println("");
			System.out.println("**Most Expensive price flight details are: **");
			System.out.println("              Price: $" + mostExpensivePrice);
			System.out.println("              Duration: " + FlightBookingUtils.formatDuration(durations.get(mostExpensiveIndex)));
			System.out.println("              Flight Name: " + flightNames.get(mostExpensiveIndex));
			System.out.println("");

			System.out.println("****************************");

			System.out.println("**Shortest Duration flight details are: **");
			System.out.println("              Duration: " + FlightBookingUtils.formatDuration(shortestDuration));
			System.out.println("              Price: $" + prices.get(shortestIndex));
			System.out.println("              Flight Name: " + flightNames.get(shortestIndex));

			System.out.println("");
			System.out.println("**Longest Duration flight details are: **");
			System.out.println("              Duration: " + FlightBookingUtils.formatDuration(longestDuration));
			System.out.println("              Price: $" + prices.get(longestIndex));
			System.out.println("              Flight Name: " + flightNames.get(longestIndex));

			FlightBookingUtils.pauseExecution(3000);
		} else {
			System.out.println("No flight data available to display.");
		}
	}



}
