package locators;

import org.openqa.selenium.By;

public final class ProductDetailLocators {
    private ProductDetailLocators(){}

    public static final By PRODUCT_NAME = By.className("inventory_details_name");
    public static final By DESCRIPTION = By.className("inventory_details_desc");
    public static final By PRICE = By.className("inventory_details_price");
    public static final By BACK_BTN = By.id("back-to-products");
    public static final By ADD_TO_CART_BTN = By.cssSelector("button[id^='add-to-cart']");
    public static final By REMOVE_BTN = By.cssSelector("button[id^='remove']");

    public static final By PRODUCT_IMAGE = By.className("inventory_details_img");
}
