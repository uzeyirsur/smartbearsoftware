package com.example.step_definitions;


import com.example.utilities.CommonExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class OrderStepDefs extends BaseStep {
    Map<String, String> excelData;

    @Given("the user wants to test the scenario {string} by retrieving the test data from Excel Workbook : {string} Sheet :  {string}")
    public void the_user_wants_to_test_scenario_by_retrieving_test_data_from_Excel(String testCase, String excelWorkbook, String sheet) {
        try {
            CommonExcelReader readExcel = new CommonExcelReader();
            excelData = readExcel.getDataFromExcel(testCase, excelWorkbook, sheet);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


    @Given("the user wants to test test case : {string} by retrieving the test data from Excel Workbook: {string} Sheet: {string}")
    public void the_user_gets_test_data(String testCase, String excelWorkbook, String sheet) {
        try {
            excelData = new CommonExcelReader().getDataFromExcel(testCase, excelWorkbook, sheet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String string) {
        pages.webOrdersPage().navigateTo(string);
    }


    //| Customer name | Street  | City     | State    | Zip   |
    @When("the user enters address information:")
    public void the_user_enters_address_information(List<Map<String, String>> dataTable) {
        Map<String, String> values = dataTable.get(0);
        pages.orderPage().enterAddressInformation(values.get("Customer name"),
                values.get("Street"),
                values.get("City"),
                values.get("State"),
                values.get("Zip"));
    }



    @When("the user enters address information: Customer Name {string}, Street {string}, City {string}, State {string}, Zip {string}")
    public void the_user_enters_address_information_with_Customer_Name_Street_City_State_Zip(String customerName, String street, String city, String state, String zip) {
        pages.orderPage().enterAddressInformation(
                customerName,
                street,
                city,
                state,
                zip);
    }


    @When("the user enters address and payment information")
    public void the_user_enters_address_and_payment_information() {
        pages.orderPage().enterAddressInformation(
                excelData.get("Customer_name"),
                excelData.get("Street"),
                excelData.get("City"),
                excelData.get("State"),
                excelData.get("Zip"));

        pages.orderPage().enterPaymentInformation(excelData.get("Card"),
                excelData.get("Card_Nr"),
                excelData.get("Expire_date"));

    }

    @When("the user enters product information")
    public void the_user_enters_product_information_from_excel() {

        String productValue = excelData.get("Product");
        String quantityValue = excelData.get("Quantity");
        String pricePerUnitValue = excelData.get("Price_per_unit");
        String discount = excelData.get("Discount");

        pages.orderPage().setProductType(productValue);
        pages.orderPage().enterQuantity(quantityValue);
        pages.orderPage().enterPricePerUnit(pricePerUnitValue);
        pages.orderPage().enterDiscount(discount);
    }

    // | Card | Card Nr:  | Expire date (mm/yy) |
    @When("the user enters payment information:")
    public void the_user_enters_payment_information(List<Map<String, String>> dataTable) {
        Map<String, String> paymentInfo = dataTable.get(0);
        pages.orderPage().enterPaymentInformation(paymentInfo.get("Card"),
                paymentInfo.get("Card Nr:"),
                paymentInfo.get("Expire date (mm/yy)"));
    }

    @When("the user enters payment information: Card {string}, Card Number {string}, Expire Date with mm yy date format {string}")
    public void the_user_enters_payment_information_with_different_data(String card, String cardNumber, String expireDate) {
        pages.orderPage().enterPaymentInformation(card,
                cardNumber,
                expireDate);
    }


    // | Product   | Quantity | Price per unit | Discount |
    @When("the user enters product information:")
    public void the_user_enters_product_information(List<Map<String, String>> dataTable) {
        Map<String, String> values = dataTable.get(0);
        pages.orderPage().setProductType(values.get("Product"));
        pages.orderPage().enterQuantity(values.get("Quantity"));
        pages.orderPage().enterPricePerUnit(values.get("Price per unit"));
        pages.orderPage().enterDiscount(values.get("Discount"));

    }

    @When("the user enters product information: Product {string}, Quantity {string}, Price per unit  {string}, Discount  {string}")
    public void the_user_enters_product_information_Product_Quantity_Price_per_unit_Discount(String product, String quantity, String pricePerUnit, String discount) {
        pages.orderPage().setProductType(product);
        pages.orderPage().enterQuantity(quantity);
        pages.orderPage().enterPricePerUnit(pricePerUnit);
        pages.orderPage().enterDiscount(discount);
    }


    @When("the user click on calculate button")
    public void the_user_click_on_calculate_button() {
        pages.orderPage().clickToCalculate();
    }


    @Then("the user should verify that total {int} is displayed")
    public void the_user_should_verify_that_total_is_displayed(Integer totalValue) {
        Assert.assertEquals(String.valueOf(totalValue), pages.orderPage().getTotal());
    }

    @Then("the user should verify that displayed total amount")
    public void the_user_should_verify_that_displayed_total_amount() {
        Assert.assertEquals(String.valueOf(excelData.get("Total")), pages.orderPage().getTotal());
    }

    @Then("the user click on process button")
    public void the_user_click_on_process_button() {
        pages.orderPage().clickOnProcessButton();

    }


}
