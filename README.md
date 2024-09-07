```
FlightBookingAnalyzer
│
├── src/main/java                 // Main source files
│
├── src/main/resources            // Resource files
│
├── src/test/java                 // Test source files
│   └── com.flightBooking.googleFlights
│       ├── FlightBookingUtils.java        // Utility methods for flight booking tests
│       ├── FlightDetailsData.java         // Data class for flight details
│       ├── FlightSearchPageObject.java    // Page Object for flight search page
│       ├── FlightSearchStepDefinitions.java // Step definitions for feature-based testing
│       ├── FlightTestData.java            // Test data class for managing flight data
│       └── XPathConstants.java            // XPath selectors used in test cases
│
├── src/test/resources            // Resources for testing
│   └── Feature.feature            // BDD feature file for flight booking scenario
│
├── pom.xml                       // Maven configuration file
├── testng.xml                    // TestNG suite configuration file
├── target                        // Output folder for compiled code and reports
├── README.md                     // Project documentation (this file)

**How to Run the Project**
  1.  **Prerequisites**

     >>> JDK 1.8 or higher
     >>> Maven installed
     >>> TestNG dependencies (automatically managed by Maven)
     >>> Any additional libraries can be added via pom.xml

 2. **Running Tests**
    **Command Line**
       >>> mvn clean test
  3. **Reports**
      >>> After running tests, the test results can be found in the target/test-output folder. These results include TestNG reports which can be viewed by opening the index.html file in a browser

**Project Details**
    **Main Components:**
      **FlightBookingUtils.java**
           >>> Contains utility methods used throughout the flight booking tests.
      **FlightDetailsData.java**
           >>> A data class to manage and store flight booking details like departure, destination, dates, etc.
    **FlightSearchPageObject.java**
         >>> Implements the Page Object Model (POM) for the flight search page, interacting with elements like input fields and buttons.
   **FlightSearchStepDefinitions.java**
        >>> Contains the step definitions that link Gherkin syntax in the .feature files to Java methods.
  **FlightTestData.java**
       >>> Provides test data for flight searches.
   **XPathConstants.java**

     >>> Centralized file for managing all XPath selectors used in the tests to maintain consistency and ease of update.
  **Feature.feature**
    >>> This file defines the flight booking feature using BDD style with Gherkin syntax.
**Dependencies**
All dependencies are managed through Maven. Ensure the required dependencies (e.g., TestNG, Selenium, etc.) are listed in pom.xml.


```

