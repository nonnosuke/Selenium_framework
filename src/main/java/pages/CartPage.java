package pages;

import base.BasePage;
import locators.CartLocators;
import org.openqa.selenium.WebDriver;
import pages.components.CartItemsComponent;
import pages.components.FooterComponent;
import pages.components.HeaderComponent;

public class CartPage extends BasePage {

    private final HeaderComponent header;
    private final FooterComponent footer;
    private final CartItemsComponent cartItems;

    public CartPage (WebDriver driver, int timeoutSeconds){
        super(driver, timeoutSeconds);
        this.footer = new FooterComponent(driver, timeoutSeconds);
        this.header = new HeaderComponent(driver, timeoutSeconds);
        this.cartItems = new CartItemsComponent(driver, timeoutSeconds);
    }

    public HeaderComponent header(){
        return header;
    }

    public FooterComponent footer(){
        return footer;
    }


    public boolean loadedPage(){
        return getText(CartLocators.PAGE_TITLE).equals("Your Cart");
    }

    public CartItemsComponent cartItems(){
        return cartItems;
    }

    public CheckoutPage checkout(){
        click(CartLocators.CHECKOUT_BTN);
        return new CheckoutPage(driver, timeoutSeconds);
    }

    public InventoryPage continueShopping(){
        click(CartLocators.CONTINUE_SHOPPING_BTN);
        return new InventoryPage(driver, timeoutSeconds);
    }

    public CartPage removeProduct(String productName){
        click(CartLocators.REMOVE_BTN(productName));
        return this;
    }

    public int getCartBadgeCount(){
        return header().getCartBadgeCount();
    }
}
