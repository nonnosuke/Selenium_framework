package pages;

import base.BasePage;
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

    public void enterFirstName(String firstName){
        type(firstNameInput, firstName);
    }

    public void enterLastName(String lastName){
        type(lastNameInput, lastName);
    }

    public void enterPostalCode(String postalcode){
        type(postalCodeInput, postalcode);
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


    public CartPage cancel(){
        click(cancelBtn);
        return new CartPage(driver, timeoutSeconds);
    }

    public String getErrorMessage(){
        return getText(errorMessage);
    }


}
