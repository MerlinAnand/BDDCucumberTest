Feature: Navigate the existing web site

  Background:
    Given I open "testapp" url

  @personalTable
  Scenario Outline: User one way domestic trip search and Add Baggage
    When user search the domestic flights "<From>" and "<To>"
    Then user selects the red deal and add to trip
    Then user add additional <Bags>
    Examples:
      | From   |  To        | Bags |
      | Sydney | Brisbane   |  1   |
      | Sydney | Melbourne  |  2   |
      | Sydney | Perth      |  3   |
      | Sydney | Canberra   |  4   |
      | Sydney | Gold Coast |  5   |