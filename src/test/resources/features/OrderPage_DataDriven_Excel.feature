@OrderPage_Excel_Data_Driven
Feature: Add order with different users (Scenario Outline)
  As use, I want to be able to create new orders with different user information.

  Background:
    Given the user navigates to login page
    And  the user logged in with valid credentials, "Tester" as username and "test" as password
    And the user is on the web orders page


  Scenario Outline: The user create an order for <"TestCase">
    And the user wants to test test case : "<TestCase>" by retrieving the test data from Excel Workbook: "SmartbareSoftwere" Sheet: "SmartbareSoftwere_OrderPage"
    And the user navigates to "Order" page
    When the user enters product information
    And the user click on calculate button
    Then the user should verify that displayed total amount
    When the user enters address and payment information
    And the user click on process button
    And the user navigates to "View all orders" page

    Examples:
      | TestCase                         |
      | TC_1_Order_US_UnitedState_Client |
      | TC_2_Order_ES_Spain_Client       |
      | TC_3_Order_EG_Egypt_Client       |
      | TC_4_Order_KR_SouthKorea_Client  |
      | TC_5_Order_TR_Turkiye_Client     |




