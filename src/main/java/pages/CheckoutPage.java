package pages;

import base.BasePage;
import locators.CheckoutLocators;
import models.CheckoutData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    public CheckoutPage (WebDriver driver, int timeoutSeconds){
        super(driver, timeoutSeconds);
    }

    public boolean loadedPage(){
        System.out.println(getText(CheckoutLocators.PAGE_TITLE));
        return "Checkout: Your Information".equals(getText(CheckoutLocators.PAGE_TITLE));
    }

    public CheckoutPage enterFirstName(String firstName){
        type(CheckoutLocators.FIRST_NAME_INPUT, firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName){
        type(CheckoutLocators.LAST_NAME_INPUT, lastName);
        return this;
    }

    public CheckoutPage enterPostalCode(String postalcode){
        type(CheckoutLocators.POSTAL_CODE_INPUT, postalcode);
        return this;
    }

    public CheckoutOverviewPage continueBtn(){
        click(CheckoutLocators.CONTINUE_BTN);
        return new CheckoutOverviewPage(driver, timeoutSeconds);
    }

    public CheckoutOverviewPage enterInfo(
            String firstName,
            String lastName,
            String postalCode) {

        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);

        return continueBtn();
    }

    public CheckoutPage fill(CheckoutData data){
        enterFirstName(data.firstName());
        enterLastName(data.lastName());
        enterPostalCode(data.postalCode());
        return this;
    }

    public CartPage cancel(){
        click(CheckoutLocators.CANCEL_BTN);
        return new CartPage(driver, timeoutSeconds);
    }

    public String getErrorMessage(){
        return getText(CheckoutLocators.ERROR_MESSAGE);
    }


}
