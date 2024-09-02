package yelseSoft.com;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightSearchAutomation {

    public static void main(String[] args) {
       
        FirefoxOptions options = new FirefoxOptions();
        WebDriver booking = new FirefoxDriver(options);

        try {
            booking.get("https://www.google.com/travel/flights?gl=US&hl=en-US");
            booking.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(booking, Duration.ofSeconds(20));

          
            String departureCity = ""; 
            String destinationCity = ""; 
            String departureDate = ""; 
            String returnDate = ""; 

            // Perform flight search
            FlightSearchPage.searchForFlights(booking, wait, departureCity, destinationCity);

            // Extract flight details
            FlightDetails.extractFlightDetails(booking, wait,departureCity,destinationCity,departureDate,returnDate);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           booking.quit();
        }
    }
}
