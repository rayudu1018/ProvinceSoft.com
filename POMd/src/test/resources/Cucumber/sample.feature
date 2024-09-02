Feature: Flight Search Automation

  Scenario Outline: Search for flights and extract flight details
    Given the user is on the flight search page
    When the user searches for flights from "<departureCity>" to "<destinationCity>"
    Then the flight details should be extracted and displayed

    Examples:
      | departureCity | destinationCity |
      | New York       | London           |
      | San Francisco  | Paris            |
      | Los Angeles    | New York         |