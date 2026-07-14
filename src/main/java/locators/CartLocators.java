package locators;

import org.openqa.selenium.By;

public final class CartLocators {

    private CartLocators(){}

    public static final By PAGE_TITLE = By.className("title");

    public static final By CHECKOUT_BTN = By.id("checkout");

    public static final By CONTINUE_SHOPPING_BTN = By.id("continue-shopping");

    public static final By CART_ITEM = By.className("cart_item");

    public static By REMOVE_BTN(String productName) {
        return By.xpath("//div[@class='cart_item'][.//div[text()='" + productName + "']]//button");
    }

    public static By product(String productName){
        return By.xpath("//div[text()='" + productName + "']");
    }

}
