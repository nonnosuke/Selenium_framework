package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    private final By pageTitle = By.className("title");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");

    public boolean loadedPage(){
        return "Products".equals(getText(pageTitle));
    }

    public void addProductToCart(String productName){
        By addBtn = By.xpath(
                "//div[contains(@class,'inventory_item')][.//div[text()='" + productName + "']]//button"
        );
        click(addBtn);
    }

    public int getCartCount(){
        return Integer.parseInt(getText(cartBadge));
    }

    public CartPage openCart(){
        click(cartLink);
        return new CartPage(driver, timeoutSeconds);
    }
}
