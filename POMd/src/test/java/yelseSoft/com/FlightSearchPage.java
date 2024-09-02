package yelseSoft.com;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightSearchPage {

    public static void searchForFlights(WebDriver driver, WebDriverWait wait, String departureCity, String destinationCity,String departureDate,String returnDate) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement exploreButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AllXpaths.EXPLORE_BUTTON)));
        js.executeScript("arguments[0].scrollIntoView(true);", exploreButton);
        js.executeScript("arguments[0].click();", exploreButton);

        WebElement flightSearch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AllXpaths.FLIGHT_SEARCH_INPUT)));
        flightSearch.clear();
       
        flightSearch.sendKeys(departureCity);

        WebDriverWait waitForResults1 = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        WebElement sResult = waitForResults1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='zsRT0d'])[1]")));
        sResult.click();

        WebElement flightSearchTo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AllXpaths.FLIGHT_SEARCH_TO_INPUT)));
        flightSearch.clear();
       
        flightSearchTo.sendKeys(destinationCity);
        
        WebDriverWait waitForResults = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstResult = waitForResults.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='zsRT0d'])[1]")));
        firstResult.click();

        WebElement findFlight = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AllXpaths.FIND_FLIGHT_BUTTON)));
        findFlight.click();
        pauseExecution(3000);
    }
    
    public static void pauseExecution(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
