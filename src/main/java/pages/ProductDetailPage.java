package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.components.FooterComponent;
import pages.components.HeaderComponent;

public class ProductDetailPage extends BasePage {

    private final HeaderComponent header;
    private final FooterComponent footer;

    public ProductDetailPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
        this.footer = new FooterComponent(driver, timeoutSeconds);
        this.header = new HeaderComponent(driver, timeoutSeconds);
    }

    public HeaderComponent header(){
        return header;
    }

    public FooterComponent footer(){
        return footer;
    }

    private final By productName = By.className("inventory_details_name");
    private final By description = By.className("inventory_details_desc");
    private final By price = By.className("inventory_details_price");
    private final By backBtn = By.id("back-to-products");
    private final By addToCartBtn = By.cssSelector("button[id^='add-to-cart']"); //^= means start from '~'
    private final By removeBtn = By.cssSelector("button[id^='remove']");

    public boolean loadedPage(){
        return isDisplayed(productName);
    }

    public String getProductName(){
        return getText(productName);
    }

    public String getDescription(){
        return getText(description);
    }

    public double getPrice(){
        return Double.parseDouble(getText(price).replace("$", ""));
    }

    public ProductDetailPage addToCart(){
        click(addToCartBtn);
        return this;
    }

    public boolean addBtnDisplayed(){
        return isDisplayed(addToCartBtn);
    }

    public boolean removeBtnDisplayed(){ return isDisplayed(removeBtn); }

    public ProductDetailPage removeFromCart(){
        click(removeBtn);
        return this;
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
