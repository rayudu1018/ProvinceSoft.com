package yelseSoft.com;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "flightData")
    public Object[][] flightData() {
        return new Object[][]{
            {"New York", "London"},
            {"San Francisco", "Paris"},
            {"Los Angeles", "New York"}
        };
    }
}
