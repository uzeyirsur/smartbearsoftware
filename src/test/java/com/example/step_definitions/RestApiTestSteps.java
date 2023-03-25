package com.example.step_definitions;

import com.dover.assesment.pojos.payment.*;
import com.example.pojos.payment.PaymentRequestPayloadGeneratorByConstructor;
import com.example.pojos.payment.PaymentRequestPayloadGeneratorByUsingBuilders;
import com.example.pojos.payment.PaymentRequestPayloadGeneratorByUsingBuildersVersion2;
import com.example.pojos.payment.PaymentRequestPayloadGeneratorGetterAndSetter;
import com.example.pojos.payment.response.PaymentResponse;

import com.example.utilities.APIUtils;
import com.example.utilities.CommonExcelReader;
import com.example.utilities.PropertyFileReader;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;

import java.util.Map;

public class RestApiTestSteps {


    private Response response;
    String payload;
    String responseAsString = "{\"status\":\"accepted\",\"cdtrInf\":{\"nm\":\"EmilyWilliams\",\"adr\":{\"crty\":\"UnitedStates\",\"city\":\"NewYork\",\"pstcd\":\"10001\",\"bldNb\":\"45\"}}}";
    Map<String, String> excelData;

    @Given("the user wants to test test case : {string} by retrieving the test data from Excel Workbook: {string} Sheet: {string} for API")
    public void the_user_gets_test_data(String testCase, String excelWorkbook, String sheet) {
        try {
            excelData = new CommonExcelReader().getDataFromExcel(testCase, excelWorkbook, sheet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @When("The client calls {string} url")
    public void user_is_on_the_login_page(String url) {
        response = APIUtils.sendPostRequest(url, payload);

    }

    @Then("Verify http status code {string}")
    public void the_user_should_be_logged_in(String expectedHttpStatuscode) {
        String actualHttpStatuscode = String.valueOf(response.getStatusCode());
        //(String message, Object expected, Object actual) {
        Assert.assertEquals("Http Status code is not correct :\n", expectedHttpStatuscode, actualHttpStatuscode);
    }

    @Then("Check the result")
    public void the_user_checks_Response() {


        response.prettyPrint();
    }

    @And("the user builds payload for payment engine call")
    public void theUserBuildsPayloadForPaymentEngineCall() {
        try {


            PaymentRequestPayloadGeneratorGetterAndSetter paymentRequestPayloadGeneratorSimpleobj = new PaymentRequestPayloadGeneratorGetterAndSetter();
            String payload1 = paymentRequestPayloadGeneratorSimpleobj.buildPayload(excelData);

            System.out.println("\nPaymentRequestPayloadGeneratorGetterAndSetter :\n" + payload1);


            PaymentRequestPayloadGeneratorByConstructor paymentRequestPayloadGeneratorByConstructor = new PaymentRequestPayloadGeneratorByConstructor();
            String payload2 = paymentRequestPayloadGeneratorByConstructor.buildPayload(excelData);

            System.out.println("\nPaymentRequestPayloadGeneratorByConstructor : \n" + payload2);


            PaymentRequestPayloadGeneratorByUsingBuilders paymentRequestPayloadGeneratorByUsingBuilders = new PaymentRequestPayloadGeneratorByUsingBuilders();
            String payload3 = paymentRequestPayloadGeneratorByUsingBuilders.buildPayload(excelData);

            System.out.println("\nPaymentRequestPayloadGeneratorByUsingBuilders : \n" + payload3);


            PaymentRequestPayloadGeneratorByUsingBuildersVersion2 paymentRequestPayloadGeneratorByUsingBuildersVersion2 = new PaymentRequestPayloadGeneratorByUsingBuildersVersion2();
            String payload4 = paymentRequestPayloadGeneratorByUsingBuildersVersion2.buildPayload(excelData);

            System.out.println("\nPaymentRequestPayloadGeneratorByUsingBuildersVersion2 : \n" + payload4);


            payload = payload1;


            String prettyPrintedPAyload = APIUtils.prettyPrintJson(payload1);

            System.out.println("\n\nPretty Printed Request Body :\n" + prettyPrintedPAyload);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }


    @Then("Verify response values")
    public void verifyResponseValues() {

        /*  Examle : Test Case 1
         {
            "status": "accepted",
            "cdtrInf": {
              "nm": "Emily Williams",
              "crty": "United States",
              "city": "New York",
              "pstcd": "10001",
              "bldNb": "45"
            }
          }
         */

        try {
            String yy = responseAsString;
            String responseAsString = PropertyFileReader.getPropertyValue("src/test/resources/TestData/APIResponseExample.properties", excelData.get("Test Case :"));

            yy = responseAsString;
            ObjectMapper objectMapper = getObjectMapper();

            String xx = response.getBody().asString();
            yy = responseAsString;

            PaymentResponse paymentResponseObj = objectMapper.readValue(responseAsString, PaymentResponse.class);

            String actualPaymentStatus = paymentResponseObj.getStatus();
            String expectedPaymentStatus = excelData.get("paymentStatus");


            String actualCreditorCountry = paymentResponseObj.getCdtrInf().getAdr().getCrty();
            String expectedCreditorCountry = excelData.get("cdtrInf.adr.crty");


            Assert.assertEquals("Payment Status ", expectedPaymentStatus, actualPaymentStatus);

            Assert.assertEquals("cdtrInf.adr.crty :  ", expectedCreditorCountry, actualCreditorCountry);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper;
    }


}
