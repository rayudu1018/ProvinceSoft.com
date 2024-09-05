package com.flightBooking.googleFlights;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(threadPoolSize = 3, invocationCount = 3, timeOut = 10000)
public class FlightSearchStepDefinitions {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        driver.set(new FirefoxDriver(options));
        wait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(40)));
    }

    @AfterMethod
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
        }
        driver.remove();  
        wait.remove();    
    }

    @Test(dataProvider = "flightTestData", dataProviderClass = FlightTestData.class)
    public void testFlightSearch(String departureCity, String destinationCity, String departureDate, String returnDate) {
        driver.get().get("https://www.google.com/travel/flights?gl=US&hl=en-US");
        driver.get().manage().window().maximize();

        FlightSearchPageObject.searchForFlights(driver.get(), wait.get(), departureCity, destinationCity, departureDate, returnDate);
        FlightDetailsData.extractFlightDetails(driver.get(), wait.get(), departureCity, destinationCity, departureDate, returnDate);
    }

    @Test(groups = "smoke", dataProvider = "flightTestData", dataProviderClass = FlightTestData.class)
    public void testFlightSearchSmoke(String departureCity, String destinationCity, String departureDate, String returnDate) {
        driver.get().get("https://www.google.com/travel/flights?gl=US&hl=en-US");
        driver.get().manage().window().maximize();

        FlightSearchPageObject.searchForFlights(driver.get(), wait.get(), departureCity, destinationCity, departureDate, returnDate);
        FlightDetailsData.extractFlightDetails(driver.get(), wait.get(), departureCity, destinationCity, departureDate, returnDate);
    }

    @Test(groups = "regression", dataProvider = "flightTestData", dataProviderClass = FlightTestData.class)
    public void testFlightSearchRegression(String departureCity, String destinationCity, String departureDate, String returnDate) {
        driver.get().get("https://www.google.com/travel/flights?gl=US&hl=en-US");
        driver.get().manage().window().maximize();

        FlightSearchPageObject.searchForFlights(driver.get(), wait.get(), departureCity, destinationCity, departureDate, returnDate);
        FlightDetailsData.extractFlightDetails(driver.get(), wait.get(), departureCity, destinationCity, departureDate, returnDate);
    }
}
