package yelseSoft.com;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class FlightSearchSteps {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        driver.set(new FirefoxDriver(options));
        wait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(20)));
    }

    @AfterMethod
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }

    @Test(dataProvider = "flightData", dataProviderClass = TestData.class)
    public void testFlightSearch(String departureCity, String destinationCity) {
        driver.get().get("https://www.google.com/travel/flights?gl=US&hl=en-US");
        driver.get().manage().window().maximize();

        FlightSearchPage.searchForFlights(driver.get(), wait.get(), departureCity, destinationCity);
        FlightDetails.extractFlightDetails(driver.get(), wait.get(), departureCity, destinationCity);
    }
}

