package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompleteOrderPage extends BasePage {

    public CompleteOrderPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    private final By pageTitle = By.className("title");
    private final By completeText = By.className("complete-header");
    private final By backHomeBtn = By.id("back-to-products");

    public boolean loadedPage(){
        return "Checkout: Complete!".equals(getText(pageTitle));
    }

    public String getText(){
        return getText(completeText);
    }

    public InventoryPage backHome(){
        click(backHomeBtn);
        return new InventoryPage(driver, timeoutSeconds);
    }
}
