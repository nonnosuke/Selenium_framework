package pages;

import base.BasePage;
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

    private final By pageTitle = By.className("title");
    private final By sortDropdown = By.className("product_sort_container");
    private final By inventoryItemNames = By.className("inventory_item_name");
    private final By inventoryPrices = By.className("inventory_item_price");


    public boolean loadedPage(){
        return "Products".equals(getText(pageTitle));
    }

    public InventoryPage addProductToCart(String productName){
        By addBtn = By.xpath(
                "//div[contains(@class,'inventory_item')][.//div[text()='" + productName + "']]//button"
        );
        click(addBtn);
        return this;
    }

    public InventoryPage removeProductFromCart(String productName){
        By removeBtn = By.xpath("//div[text()='" + productName +
                "']/ancestor::div[@class='inventory_item']//button"
        );
        click(removeBtn);
        return this;
    }

    private void selectSort(String value){
        Select select = new Select(driver.findElement(sortDropdown));
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
        return driver.findElements(inventoryItemNames)
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<Double> getProductPrices(){
        return driver.findElements(inventoryPrices)
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
        click(By.xpath(("//div[text()='" + productName + "']")));
        return new ProductDetailPage(driver, timeoutSeconds);
    }
}
