package pages.components;

import base.BasePage;
import locators.InventoryLocators;
import locators.ProductDetailLocators;
import org.openqa.selenium.*;

import java.util.List;

public class InventoryItemsComponent extends BasePage {

    public InventoryItemsComponent(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    public List<String> getProductNames(){
        return driver.findElements(InventoryLocators.INVENTORY_ITEM_NAMES)
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<Double> getProductPrices(){
        return driver.findElements(InventoryLocators.INVENTORY_PRICES)
                .stream()
                .map(e -> e.getText().replace("$", ""))
                .map(Double::parseDouble)
                .toList();
    }

    public String getProductImage(String productName){
        By image = InventoryLocators.productImage(productName);
        return visible(image).getAttribute("src");
    }

    public String getProductDetailPageImage(){
        By image = ProductDetailLocators.PRODUCT_IMAGE;
        return visible(image).getAttribute("src");
    }

    public Rectangle getProductCardRect(String productName){
        return visible(InventoryLocators.productCard(productName))
                .getRect();
    }

    public Rectangle getAddBtnRect(String productName){
        return visible(InventoryLocators.addBtn(productName))
                .getRect();
    }

    public boolean isProductNameRightAligned(String productName){
        //return visible(InventoryLocators.product(productName)).getAttribute("class").contains("align_right");
        return visible(InventoryLocators.product(productName)).getCssValue("text-align").equals("right");
    }

    public String getBtnText(String productName) {
        By btn = InventoryLocators.button(productName);
        return getText(btn);
    }
}
