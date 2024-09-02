package yelseSoft.com;

public class AllXpaths {

	// Locators for departure and return dates
	public static final String DEPARTURE_DATE_INPUT = "(//div[@class='BLohnc q5Vmde'])[1]";
	public static final String DEPARTURE_DATE = "//div[contains(@aria-label,'Sunday, September 3, 2024')]";
	public static final String RETURN_DATE_INPUT = "(//input[@placeholder='Return'])[2]";
	public static final String RETURN_DATE = "//div[contains(@aria-label,'Thursday, September 5, 2024')]";

	// Locators for search fields and buttons
	public static final String WHERE_TO_INPUT = "//input[@placeholder='Where to?']";
	public static final String DONE_BUTTON = "(//span[text()='Done'])[2]";
	public static final String EXPLORE_BUTTON = "(//span[contains(text(),'Explore')])[2]";
	public static final String FLIGHT_SEARCH_INPUT = "(//input[contains(@aria-label,'Where from?')])[2]";
	public static final String FLIGHT_SEARCH_TO_INPUT = "(//input[contains(@aria-label,'Where to?')])[2]";
	public static final String FIND_FLIGHT_BUTTON = "//a[@aria-label=\"View flights\"]";

	// Locators for flight details
	public static final String FLIGHT_LIST_UL = "//ul[@class='Rk10dc']";
	public static final String FLIGHT_LIST_ITEM = ".//li";
	public static final String DEPARTURE_TIME = ".//span[contains(@aria-label, 'Departure time')]";
	public static final String ARRIVAL_TIME = ".//span[contains(@aria-label, 'Arrival time')]";
	public static final String TOTAL_TIME = ".//div[@class='gvkrdb AdWm1c tPgKwe ogfYpf']";
	public static final String FROM_AIRPORT = ".//div[@class='QylvBf'][1]//span[@aria-label='']";
	public static final String TO_AIRPORT = ".//div[@class='QylvBf'][2]//span[@aria-label='']";
	public static final String PRICE = ".//span[@aria-label and @role='text' and contains(text(), '$')]";
	public static final String ROUND_TRIP = ".//div[@class='N872Rd sSHqwe I11szd POX3ye']";
	public static final String FLIGHT_NAME = ".//div[contains(@class,\"sSHqwe tPgKwe ogfYpf\")]";
	public static final String MORE_FLIGHTS = "//span[@class=\"bEfgkb \"]";
	public static final String SECOND_FLIGHTS = "//h3[contains(text(),'Other departing flights')]/following-sibling::ul[@class='Rk10dc']\r\n"
			+ "";

}
