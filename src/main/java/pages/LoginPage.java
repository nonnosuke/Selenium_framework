package pages;

import base.BasePage;
import locators.LoginLocators;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, int timeoutSeconds){
        super(driver, timeoutSeconds);
    }

    public void login(String username, String password){
        type(LoginLocators.USER_NAME_FIELD, username);
        type(LoginLocators.PASSWORD_FIELD, password);
        click(LoginLocators.LOGIN_BTN);
    }

    public boolean loadedPage(){
        return isDisplayed(LoginLocators.LOGIN_BTN);
    }

    public String getErrorMessage(){
        return getText(LoginLocators.ERROR_MESSAGE);
    }

}
