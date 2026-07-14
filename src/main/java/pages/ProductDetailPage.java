package pages;

import base.BasePage;
import locators.ProductDetailLocators;
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

    public boolean loadedPage(){
        return isDisplayed(ProductDetailLocators.PRODUCT_NAME);
    }

    public String getProductName(){
        return getText(ProductDetailLocators.PRODUCT_NAME);
    }

    public String getDescription(){
        return getText(ProductDetailLocators.DESCRIPTION);
    }

    public double getPrice(){
        return Double.parseDouble(getText(ProductDetailLocators.PRICE).replace("$", ""));
    }

    public ProductDetailPage addToCart(){
        click(ProductDetailLocators.ADD_TO_CART_BTN);
        return this;
    }

    public boolean addBtnDisplayed(){
        return isDisplayed(ProductDetailLocators.ADD_TO_CART_BTN);
    }

    public boolean removeBtnDisplayed(){ return isDisplayed(ProductDetailLocators.REMOVE_BTN); }

    public ProductDetailPage removeFromCart(){
        click(ProductDetailLocators.REMOVE_BTN);
        return this;
    }

    public int getCartCount(){
        return header.getCartBadgeCount();
    }

    public boolean hasCartBadge(){
        return header.hasCartBadge();
    }

    public InventoryPage backToProducts(){
        click(ProductDetailLocators.BACK_BTN);
        return new InventoryPage(driver, timeoutSeconds);
    }
}
