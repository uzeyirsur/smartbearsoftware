@apiTest
Feature: RestAPI testing

  Scenario Outline: API testing
    When The client calls "<URL>" url
    Then Verify http status code "<httpStatusCode>"

    Examples:
      | URL                                  | httpStatusCode |
      | https://api.restful-api.dev/objects/ | 200            |
      | https://www.zippopotam.us/us/90210   | 200            |


  Scenario Outline: API testing
    When The client calls "<URL>" url
    Then Verify http status code "<httpStatusCode>"
    And Check the result

    Examples:
      | URL                                | httpStatusCode |
      | https://www.zippopotam.us/us/90210 | 200            |





