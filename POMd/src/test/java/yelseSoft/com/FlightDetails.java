package yelseSoft.com;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightDetails {

	public static void extractFlightDetails(WebDriver driver, WebDriverWait wait,String departureCity, String destinationCity) {
		String originalWindow = driver.getWindowHandle();
		//System.out.println("Current window address: " + originalWindow);

		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
		if (windowHandles.size() > 1) {
			String secondWindowHandle = windowHandles.get(1);
			// System.out.println("Second Window Handle: " + secondWindowHandle);
			driver.switchTo().window(secondWindowHandle);
		}

		List<Integer> prices = new ArrayList<>();
		List<Integer> durations = new ArrayList<>();
		List<String> flightNames = new ArrayList<>();

		WebElement countoful = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AllXpaths.FLIGHT_LIST_UL)));
		List<WebElement> countofli = countoful.findElements(By.xpath(AllXpaths.FLIGHT_LIST_ITEM));

		for (WebElement liElement : countofli) {
			try {
				WebElement departureTime = liElement.findElement(By.xpath(AllXpaths.DEPARTURE_TIME));
				String departure = departureTime.getText();

				WebElement arrivalTime = liElement.findElement(By.xpath(AllXpaths.ARRIVAL_TIME));
				String arrival = arrivalTime.getText();

				WebElement totalTimeElement = liElement.findElement(By.xpath(AllXpaths.TOTAL_TIME));
				String totalTime = totalTimeElement.getText();
				int durationInMinutes = parseDuration(totalTime);
				durations.add(durationInMinutes);

				WebElement fromAirport = liElement.findElement(By.xpath(AllXpaths.FROM_AIRPORT));
				WebElement toAirport = liElement.findElement(By.xpath(AllXpaths.TO_AIRPORT));
				String fromAirportCode = fromAirport.getText();
				String toAirportCode = toAirport.getText();

				WebElement priceElement = liElement.findElement(By.xpath(AllXpaths.PRICE));
				String priceText = priceElement.getText();
				int price = parsePrice(priceText);
				prices.add(price);

				WebElement flightNameElement = liElement.findElement(By.xpath(AllXpaths.FLIGHT_NAME));
				String flightName = flightNameElement.getText();
				flightNames.add(flightName);


				WebElement roundTripElement = liElement.findElement(By.xpath(AllXpaths.ROUND_TRIP));
				String roundTripText = roundTripElement.getText();

				//                System.out.println("Departure Time: " + departure + " Arrival Time: " + arrival);
				//                System.out.println("Total Duration Time: " + totalTime);
				//                System.out.println("From: " + fromAirportCode + "  To: " + toAirportCode);
				//                System.out.println("Price: " + priceText + " for: " + roundTripText);

			} catch (NoSuchElementException e) {
				System.out.println("Element not found: " + e.getMessage());
			}
		}

		// second set 
		WebElement initialUl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AllXpaths.SECOND_FLIGHTS)));
		List<WebElement> initialLiList = initialUl.findElements(By.xpath(AllXpaths.FLIGHT_LIST_ITEM));
		//System.out.println("INITIAL FLIGHTS LIST COUNT: " + initialLiList.size());


		WebElement clickButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AllXpaths.MORE_FLIGHTS)));
		clickButton.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement updatedUl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AllXpaths.SECOND_FLIGHTS)));


		List<WebElement> updatedLiList = updatedUl.findElements(By.xpath(AllXpaths.FLIGHT_LIST_ITEM));
		//System.out.println("UPDATED FLIGHTS LIST COUNT: " + updatedLiList.size());

		int totalItemCount = updatedLiList.size();
		//System.out.println("UPDATED FLIGHTS LIST COUNT: " + totalItemCount);

		// Iterate and process all items in the list
		for (int i = 0; i < totalItemCount -1; i++) {
			WebElement liElement = updatedLiList.get(i);
			try {
				WebElement departureTime = liElement.findElement(By.xpath(AllXpaths.DEPARTURE_TIME));
				String departure = departureTime.getText();

				WebElement arrivalTime = liElement.findElement(By.xpath(AllXpaths.ARRIVAL_TIME));
				String arrival = arrivalTime.getText();

				WebElement totalTimeElement = liElement.findElement(By.xpath(AllXpaths.TOTAL_TIME));
				String totalTime = totalTimeElement.getText();
				int durationInMinutes = parseDuration(totalTime);
				durations.add(durationInMinutes);

				WebElement fromAirport = liElement.findElement(By.xpath(AllXpaths.FROM_AIRPORT));
				WebElement toAirport = liElement.findElement(By.xpath(AllXpaths.TO_AIRPORT));
				String fromAirportCode = fromAirport.getText();
				String toAirportCode = toAirport.getText();

				WebElement priceElement = liElement.findElement(By.xpath(AllXpaths.PRICE));
				String priceText = priceElement.getText();
				int price = parsePrice(priceText);
				prices.add(price);

				WebElement flightNameElement = liElement.findElement(By.xpath(AllXpaths.FLIGHT_NAME));
				String flightName = flightNameElement.getText();
				flightNames.add(flightName);

				WebElement roundTripElement = liElement.findElement(By.xpath(AllXpaths.ROUND_TRIP));
				String roundTripText = roundTripElement.getText();

//				System.out.println("Departure Time: " + departure + " Arrival Time: " + arrival);
//				System.out.println("Total Duration Time: " + totalTime);
//				System.out.println("From: " + fromAirportCode + "  To: " + toAirportCode);
//				System.out.println("Price: " + priceText + " for: " + roundTripText);

			} catch (NoSuchElementException e) {
				System.out.println("Element not found: " + e.getMessage());
			}
			
		}
		
		printFlightDetails(prices, durations, flightNames, departureCity, destinationCity);}
////

		/*if (!prices.isEmpty()) {
			int cheapestPrice = Collections.min(prices);
			int cheapestIndex = prices.indexOf(cheapestPrice);
			int mostExpensivePrice = Collections.max(prices);
			int mostExpensiveIndex = prices.indexOf(mostExpensivePrice);

			System.out.println("****************************");
			System.out.println("");
			System.out.println("**Cheapest price flight details are: **");
			System.out.println("              "+"Price: $" + cheapestPrice);
			System.out.println("              "+"Duration: " + formatDuration(durations.get(cheapestIndex)));
			System.out.println("              "+"Flight Name: " + flightNames.get(cheapestIndex));

			System.out.println("**Most Expensive price flight details are: **");
			System.out.println("              "+"Price: $" + mostExpensivePrice);
			System.out.println("              "+"Duration: " + formatDuration(durations.get(mostExpensiveIndex)));
			System.out.println("              "+"Flight Name: " + flightNames.get(mostExpensiveIndex));
			System.out.println("");
		}

		if (!durations.isEmpty()) {
			int shortestDuration = Collections.min(durations);
			int shortestIndex = durations.indexOf(shortestDuration);
			int longestDuration = Collections.max(durations);
			int longestIndex = durations.indexOf(longestDuration);

			System.out.println("****************************");

			System.out.println("**Shortest Duration flight details are: **");
			System.out.println("              " + "Duration: " + formatDuration(shortestDuration));
			System.out.println("              " + "Price: $" + prices.get(shortestIndex));
			System.out.println("              " + "Flight Name: " + flightNames.get(shortestIndex));

			System.out.println("**Longest Duration flight details are: **");
			System.out.println("              " + "Duration: " + formatDuration(longestDuration));
			System.out.println("              " + "Price: $" + prices.get(longestIndex));
			System.out.println("              " + "Flight Name: " + flightNames.get(longestIndex));
		}
	}*/

	private static int parsePrice(String priceText) {
		return Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
	}

	private static int parseDuration(String durationText) {
		String[] parts = durationText.split(" ");
		int hours = 0;
		int minutes = 0;
		for (int i = 0; i < parts.length; i++) {
			if (parts[i].contains("hr")) {
				hours = Integer.parseInt(parts[i - 1]);
			} else if (parts[i].contains("min")) {
				minutes = Integer.parseInt(parts[i - 1]);
			}
		}
		return hours * 60 + minutes;
	}

	private static String formatDuration(int totalMinutes) {
		int hours = totalMinutes / 60;
		int minutes = totalMinutes % 60;
		return hours + " hr " + minutes + " min";
	}
	
	
	
 ///second
    
    private static void printFlightDetails(List<Integer> prices, List<Integer> durations, List<String> flightNames,String departureCity, String destinationCity) {
        if (!prices.isEmpty() && !durations.isEmpty() && !flightNames.isEmpty()) {
            // For Finding the cheapest and most expensive flights
            int cheapestPrice = Collections.min(prices);
            int cheapestIndex = prices.indexOf(cheapestPrice);
            int mostExpensivePrice = Collections.max(prices);
            int mostExpensiveIndex = prices.indexOf(mostExpensivePrice);

            // For Finding the shortest and longest durations
            int shortestDuration = Collections.min(durations);
            int shortestIndex = durations.indexOf(shortestDuration);
            int longestDuration = Collections.max(durations);
            int longestIndex = durations.indexOf(longestDuration);

            System.out.println("****************************");
            System.out.println("");
            System.out.println("From: " + departureCity + " To: " + destinationCity);
            System.out.println("");
            System.out.println("****************************");
         
            System.out.println("");
            System.out.println("**Cheapest price flight details are: **");
            System.out.println("              Price: $" + cheapestPrice);
            System.out.println("              Duration: " + formatDuration(durations.get(cheapestIndex)));
            System.out.println("              Flight Name: " + flightNames.get(cheapestIndex));
            
            System.out.println("");
            System.out.println("**Most Expensive price flight details are: **");
            System.out.println("              Price: $" + mostExpensivePrice);
            System.out.println("              Duration: " + formatDuration(durations.get(mostExpensiveIndex)));
            System.out.println("              Flight Name: " + flightNames.get(mostExpensiveIndex));
            System.out.println("");

            System.out.println("****************************");
            
            System.out.println("**Shortest Duration flight details are: **");
            System.out.println("              Duration: " + formatDuration(shortestDuration));
            System.out.println("              Price: $" + prices.get(shortestIndex));
            System.out.println("              Flight Name: " + flightNames.get(shortestIndex));

            System.out.println("");
            System.out.println("**Longest Duration flight details are: **");
            System.out.println("              Duration: " + formatDuration(longestDuration));
            System.out.println("              Price: $" + prices.get(longestIndex));
            System.out.println("              Flight Name: " + flightNames.get(longestIndex));
            
           
        } else {
            System.out.println("No flight data available to display.");
        }
    }

}
