package pages;

import base.BasePage;
import locators.LoginLocators;
import models.LoginData;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, int timeoutSeconds){
        super(driver, timeoutSeconds);
    }

    public InventoryPage login(String username, String password){
        type(LoginLocators.USER_NAME_FIELD, username);
        type(LoginLocators.PASSWORD_FIELD, password);
        click(LoginLocators.LOGIN_BTN);

        return new InventoryPage(driver, timeoutSeconds);
    }

    public LoginPage loginExpectFailure(String username, String password){
        type(LoginLocators.USER_NAME_FIELD, username);
        type(LoginLocators.PASSWORD_FIELD, password);
        click(LoginLocators.LOGIN_BTN);

        return this;
    }

    public InventoryPage login(LoginData user){
        return login(user.username(), user.password());
    }

    public LoginPage loginExpectFailure(LoginData user){
        return loginExpectFailure(user.username(), user.password());
    }

    public boolean loadedPage(){
        return isDisplayed(LoginLocators.LOGIN_BTN);
    }

    public String getErrorMessage(){
        return getText(LoginLocators.ERROR_MESSAGE);
    }

}
