package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderComponent extends BasePage {

    public HeaderComponent(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    private final By cartIcon = By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");

    public void openCart(){
        click(cartIcon);
    }

    public int getCartCount(){
        if(!isDisplayed(cartBadge)){
            return 0;
        }
        return Integer.parseInt(getText(cartBadge));
    }

    public boolean hasCartBadge(){
        return isDisplayed(cartBadge);
    }

}
