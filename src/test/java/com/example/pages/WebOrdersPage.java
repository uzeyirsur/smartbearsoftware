package com.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebOrdersPage extends BasePage {

    @FindBy(xpath = "//div[@class='login_info']")
    private WebElement displayedUserName;

    @FindBy(xpath = "//h1")
    private WebElement levelOneHeader;

    @FindBy(id = "ctl00_logout")
    private WebElement logoutButton;

    @FindBy(xpath = "//a[@href='Default.aspx']")
    private WebElement viewAllOrdersButton;

    @FindBy(xpath = "//a[@href='Products.aspx']")
    private WebElement viewAllOProductsButton;

    @FindBy(xpath = "//a[@href='Process.aspx']")
    private WebElement orderButton;


    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getDisplayedUserName() {
        return displayedUserName.getText();
    }

    public String getHeaderAsString(Integer levelOfHeader) {
        String result = null;
        switch (levelOfHeader) {
            case 1:
                result = levelOneHeader.getText();
                break;
        }
        return result;
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public boolean isVerticalMenuBarItemDisplayed(String verticalMenuBarItem) {
        boolean result = false;
        switch (verticalMenuBarItem) {
            case "View all orders":
                result = viewAllOrdersButton.isDisplayed();
                break;
            case "View all products":
                result = viewAllOProductsButton.isDisplayed();
                break;
            case "Order":
                result = orderButton.isDisplayed();
                break;
        }
        return result;
    }
}
