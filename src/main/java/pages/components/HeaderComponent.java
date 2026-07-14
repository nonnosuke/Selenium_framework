package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderComponent extends BasePage {

    private final MenuComponent menu;

    public HeaderComponent(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
        this.menu = new MenuComponent(driver, timeoutSeconds);
    }

    private final By cartIcon = By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By menuBtn = By.id("react-burger-menu-btn");
    private final By closeBtn = By.id("react-burger-cross-btn");

    public void openCart(){
        click(cartIcon);
    }

    public int getCartBadgeCount(){
        if(!isDisplayed(cartBadge)){
            return 0;
        }
        return Integer.parseInt(getText(cartBadge));
    }

    public boolean hasCartBadge(){
        return isDisplayed(cartBadge);
    }

    public void openMenu(){
        click(menuBtn);
    }

    public void closeMenu(){
        click(closeBtn);
    }

    public MenuComponent menu(){
        return menu;
    }
}
