@smoke @add_order
Feature: Add order with one user
  As use, I want to be able to create new orders

  Background:
      Given the user is on the login page
      And  the user logged in with valid credentials, "Tester" as username and "test" as password
      And the user is on the web orders page

  Scenario: The user successfully create an order
  This scenario tests that a user is able to successfully create an order by entering valid inputs.
    And the user navigates to "Order" page
    When the user enters address information:
      | Customer name | Street             | City    | State   | Zip  |
      | John Smith     | Leuvensestraat 19, | Antwerp | Belgium | 3290 |

    And the user enters payment information:
      | Card | Card Nr:    | Expire date (mm/yy) |
      | Visa | 12345667890 | 04/25               |
    And the user enters product information:
      | Product     | Quantity | Price per unit | Discount |
      | ScreenSaver | 12       | 20             | 10%      |
    And the user click on calculate button
    Then the user should verify that total 216 is displayed
    And the user click on process button
    And the user navigates to "View all orders" page


