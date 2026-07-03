package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.InventoryPage;
import pages.LoginPage;

public class MenuComponent extends BasePage {

    public MenuComponent(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    private final By allItemBtn = By.id("inventory_sidebar_link");
    private final By aboutBtn = By.id("about_sidebar_link");
    private final By logoutBtn = By.id("logout_sidebar_link");
    private final By resetBtn = By.id("reset_sidebar_link");

    public InventoryPage allItems(){
        click(allItemBtn);
        return new InventoryPage(driver, timeoutSeconds);
    }

    public void about(){
        click(aboutBtn);
    }

    public LoginPage logout(){
        click(logoutBtn);
        return new LoginPage(driver, timeoutSeconds);
    }

    public void resetAppState(){
        click(resetBtn);
    }
}
