@Payment_API_Test
Feature: API testing exercise

  Scenario Outline: Testing international payment api
    Given the user wants to test test case : "<TestCase>" by retrieving the test data from Excel Workbook: "API_TestData" Sheet: "Payment" for API
    And the user builds payload for payment engine call
    When The client calls "https://api.restful-api.dev/objects/" url
    Then Verify http status code "<HTTP_Status_Code>"
    Then Verify response values

    Examples:
      | TestCase              | HTTP_Status_Code |
      | TC_1_IP_From_TR_To_US | 200              |
      | TC_2_IP_From_BE_To_ES | 200              |
      | TC_3_IP_From_DE_To_UK | 200              |



