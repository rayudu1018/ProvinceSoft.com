package yelseSoft.com;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "flightData")
    public Object[][] flightData() {
        return new Object[][]{
            {"New York", "London","2024-09-09","2024-11-11"},
            {"San Francisco", "Dallas","2024-09-09","2024-11-11"},
            {"Los Angeles", "New York","2024-09-09","2024-11-11"}
        };
    }
}