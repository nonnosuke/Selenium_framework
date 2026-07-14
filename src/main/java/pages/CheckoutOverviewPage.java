package pages;

import base.BasePage;
import locators.CheckoutOverviewLocators;
import models.CartItem;
import org.openqa.selenium.WebDriver;
import pages.components.CartItemsComponent;
import pages.components.PriceSummaryComponent;

import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    private final CartItemsComponent cartItems;
    private final PriceSummaryComponent priceSummary;

    public CheckoutOverviewPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
        this.cartItems = new CartItemsComponent(driver, timeoutSeconds);
        this.priceSummary = new PriceSummaryComponent(driver, timeoutSeconds);
    }

    public boolean loadedPage(){
        return "Checkout: Overview".equals(getText(CheckoutOverviewLocators.PAGE_TITLE));
    }

    public CartItemsComponent cartItems(){
        return cartItems;
    }

    public PriceSummaryComponent priceSummary(){
        return priceSummary;
    }

    public CompleteOrderPage finish(){
        click(CheckoutOverviewLocators.FINISH_BTN);
        return new CompleteOrderPage(driver, timeoutSeconds);
    }

    public InventoryPage cancel(){
        click(CheckoutOverviewLocators.CANCEL_BTN);
        return new InventoryPage(driver, timeoutSeconds);
    }
}
