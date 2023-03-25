@API
Feature: API testing exercise

  @API
  Scenario: API testing
    Given the user wants to test test case : "TC_1_Test" by retrieving the test data from Excel Workbook: "API_TestData" Sheet: "Payment"
    When The client calls "https://api.restful-api.dev/objects/" url
    Then Verify http status code "<httpStatusCode>"

