package com.flightBooking.googleFlights;

import org.testng.annotations.DataProvider;

public class FlightTestData {

    @DataProvider(name = "flightTestData")
    public Object[][] flightData() {
        return new Object[][]{
            {"New York", "London","2024-09-09","2024-11-11"},
            {"San Francisco", "Dallas","2024-09-09","2024-11-11"},
            {"Los Angeles", "New York","2024-09-09","2024-11-11"}
        };
    }
    
    public Object[][] flightData1() {
        return new Object[][]{
            {"New York", "London","2024-09-09","2024-11-11"},
            {"San Francisco", "Dallas","2024-09-09","2024-11-11"},
            {"Los Angeles", "New York","2024-09-09","2024-11-11"}
        };
    }
    
}
