@smoke
Feature: Product Web Orders Page Feature
  As a user, we would like to be able to validate the features that have been implemented on the web orders page.

  Background:
    Given the user is on the login page
    And  the user logged in with valid credentials, "Tester" as username and "test" as password
    And the user is on the web orders page

  Scenario: In the web orders page, the level 1 header is displayed as "Web Orders".
  This scenario tests whether the web orders header is displayed or not.
    Then the user views the level 1 header is displayed as "Web Orders"

  @webOrder
  Scenario: In the vertical menu bar, items are displayed
  This scenario tests whether the vertical menu bar items are displayed or not.
    When the user is on the web orders page
    Then the user should be able to see "View all orders" section in vertical menu bar
    And the user should be able to see "View all products" section in vertical menu bar
    And the user should be able to see "Order" section in vertical menu bar

  Scenario: In the web orders page, the user attempts to log out.
  This scenario tests that a user is able to successfully log out when they click the logout button.
    When the user clicks on the logout button
    Then the user logs out and navigates to the login page

