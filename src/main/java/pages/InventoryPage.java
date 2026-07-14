package pages;

import base.BasePage;
import locators.InventoryLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.components.FooterComponent;
import pages.components.HeaderComponent;

import java.util.List;

public class InventoryPage extends BasePage {

    private final HeaderComponent header;
    private final FooterComponent footer;

    public InventoryPage(WebDriver driver, int timeoutSeconds) {
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
        return "Products".equals(getText(InventoryLocators.PAGE_TITLE));
    }

    public InventoryPage addProductToCart(String productName){
        click(InventoryLocators.addBtn(productName));
        return this;
    }

    public InventoryPage removeProductFromCart(String productName){
        click(InventoryLocators.removeBtn(productName));
        return this;
    }

    private void selectSort(String value){
        Select select = new Select(driver.findElement(InventoryLocators.SORT_DROPDOWN));
        select.selectByValue(value);
    }

    public InventoryPage sortByNameAZ(){
        selectSort("az");
        return this;
    }

    public InventoryPage sortByNameZA(){
        selectSort("za");
        return this;
    }

    public InventoryPage sortByPriceLowToHigh(){
        selectSort("lohi");
        return this;
    }

    public InventoryPage sortByPriceHighToLow(){
        selectSort("hilo");
        return this;
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

    public CartPage openCart(){
        header.openCart();
        return new CartPage(driver, timeoutSeconds);
    }

    public ProductDetailPage openProduct(String productName){
        click(InventoryLocators.product(productName));
        return new ProductDetailPage(driver, timeoutSeconds);
    }
}
