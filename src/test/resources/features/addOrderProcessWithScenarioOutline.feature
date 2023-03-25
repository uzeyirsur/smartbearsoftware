@smoke @add_order
Feature: Add order
  As use, I want to be able to create new orders with different user information.

  Background:
    Given the user is on the login page
    And  the user logged in with valid credentials, "Tester" as username and "test" as password
    And the user is on the web orders page

  Scenario Outline: The user successfully create an order for customer <CustomerName> from <City>
  This scenario tests that a user is able to successfully create an order by entering valid inputs.
    And the user navigates to "Order" page
    When the user enters address information: Customer Name "<CustomerName>", Street "<Street>", City "<City>", State "<State>", Zip "<Zip>"
    And the user enters payment information: Card "<Card>", Card Number "<CardNumber>", Expire Date with mm yy date format "<ExpireDatemmyy>"
    And the user enters product information: Product "<Product>", Quantity "<Quantity>", Price per unit  "<PricePerUnit>", Discount  "<Discount>"
    And the user click on calculate button
    Then the user should verify that total <Total> is displayed
    And the user click on process button
    And the user navigates to "View all orders" page

    Examples:
      | CustomerName    | Street          | City      | State  | Zip   | Card             | CardNumber       | ExpireDatemmyy | Product     | Quantity | PricePerUnit | Discount | Total |
      | John Smith      | 123 Main St     | Rochester | NY     | 12345 | Visa             | 1234567812345678 | 12/25          | ScreenSaver | 2        | 20           | 10%      | 40    |
      | Jane Doe        | 456 Park Ave    | Albany    | NY     | 23423 | Mastercard       | 1234567812345678 | 01/30          | MyMoney     | 10       | 100          | 8%       | 920   |
      | Maria Rodriguez | Calle de la Paz | Madrid    | Madrid | 28000 | Visa             | 3574567812345678 | 12/29          | FamilyAlbum | 6        | 80           | 15%      | 480   |
      | Ali Ahmed       | Al-Gazeera St   | Cairo     | Cairo  | 11511 | Mastercard       | 7890123456678123 | 04/25          | ScreenSaver | 8        | 20           | 10%      | 160   |
      | Kang Soo-Jin    | Gyeongnidan-gil | Seoul     | Seoul  | 04039 | American Express | 5667781233423132 | 05/32          | MyMoney     | 1000     | 100          | 8%       | 92000 |
      | Kang Soo-Jin    | Gyeongnidan-gil | Seoul     | Seoul  | 04039 | American Express | 5667781233423132 | 05/32          | MyMoney     | 1000     | 100          | 8%       | 92000 |