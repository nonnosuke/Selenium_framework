package locators;

import org.openqa.selenium.By;

public class LoginLocators {
    private LoginLocators(){}

    public static final By USER_NAME_FIELD = By.id("user-name");
    public static final By PASSWORD_FIELD = By.id("password");
    public static final By LOGIN_BTN = By.id("login-button");
    public static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");

}
