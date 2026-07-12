package pages;

import base.BasePage;
import models.CheckoutData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private final By pageTitle = By.className("title");
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By cancelBtn = By.id("cancel");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public CheckoutPage (WebDriver driver, int timeoutSeconds){
        super(driver, timeoutSeconds);
    }

    public boolean loadedPage(){
        System.out.println(getText(pageTitle));
        return "Checkout: Your Information".equals(getText(pageTitle));
    }

    public CheckoutPage enterFirstName(String firstName){
        type(firstNameInput, firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName){
        type(lastNameInput, lastName);
        return this;
    }

    public CheckoutPage enterPostalCode(String postalcode){
        type(postalCodeInput, postalcode);
        return this;
    }

    public CheckoutOverviewPage continueBtn(){
        click(continueBtn);
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
        click(cancelBtn);
        return new CartPage(driver, timeoutSeconds);
    }

    public String getErrorMessage(){
        return getText(errorMessage);
    }


}
