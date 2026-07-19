package pages.components;

import base.BasePage;
import org.openqa.selenium.*;

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
    private final By menuPanel = By.cssSelector(".bm-icon");

    public void openCart(){
        click(cartIcon);
    }

    public int getCartBadgeCount(){
        if(!isDisplayed(cartBadge)){
            return 0;
        }
        return Integer.parseInt(getText(cartBadge));
    }

    public Point getCartIconLocation(){
        return visible(cartIcon).getLocation();
    }

    public Dimension getCartIconSize(){
        return visible(cartIcon).getSize();
    }

    public boolean hasCartBadge(){
        return isDisplayed(cartBadge);
    }

    public boolean hasMenuBadge(){
        return isDisplayed(menuBtn);
    }

    public boolean hasVisualFailureClass() {
        return visible(menuPanel).getAttribute("class").contains("visual_failure");
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

    public boolean isMenuOpened(){
        return "false".equals(visible(menuPanel).getAttribute("aria-hidden"));
    }
}
