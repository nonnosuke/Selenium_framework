package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.components.HeaderComponent;

public class ProductDetailPage extends BasePage {

    private final HeaderComponent header;

    public ProductDetailPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
        this.header = new HeaderComponent(driver, timeoutSeconds);
    }

    public HeaderComponent header(){
        return header;
    }

    private final By productName = By.className("inventory_details_name");
    private final By backBtn = By.id("back-to-products");
    private final By addToCartBtn = By.cssSelector("button[id^='add-to-cart']"); //^= means start from '~'
    private final By removeBtn = By.cssSelector("button[id^='remove']");
    private final By cartBadge = By.className("shopping_cart_badge");

    public boolean loadedPage(){
        return isDisplayed(backBtn);
    }

    public String getProductName(){
        return getText(productName);
    }

    public void addToCart(){
        click(addToCartBtn);
    }

    public boolean addBtnDisplayed(){
        return isDisplayed(addToCartBtn);
    }

    public void removeFromCart(){
        click(removeBtn);
    }

    public int getCartCount(){
        return header.getCartCount();
    }

    public boolean hasCartBadge(){
        return header.hasCartBadge();
    }

    public InventoryPage backToProducts(){
        click(backBtn);
        return new InventoryPage(driver, timeoutSeconds);
    }

}
