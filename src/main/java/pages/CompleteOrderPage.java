package pages;

import base.BasePage;
import locators.CompleteOrderLocators;
import org.openqa.selenium.WebDriver;

public class CompleteOrderPage extends BasePage {

    public CompleteOrderPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    public boolean loadedPage(){
        return "Checkout: Complete!".equals(getText(CompleteOrderLocators.PAGE_TITLE));
    }

    public String getCompleteText(){
        return getText(CompleteOrderLocators.COMPLETE_TEXT);
    }

    public InventoryPage backHome(){
        click(CompleteOrderLocators.BACK_HOME_BTN);
        return new InventoryPage(driver, timeoutSeconds);
    }
}
