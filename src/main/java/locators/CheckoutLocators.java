package locators;

import org.openqa.selenium.By;

public final class CheckoutLocators {

    private CheckoutLocators(){}

    public static final By PAGE_TITLE = By.className("title");
    public static final By FIRST_NAME_INPUT = By.id("first-name");
    public static final By LAST_NAME_INPUT = By.id("last-name");
    public static final By POSTAL_CODE_INPUT = By.id("postal-code");
    public static final By CONTINUE_BTN = By.id("continue");
    public static final By CANCEL_BTN = By.id("cancel");
    public static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");
}
