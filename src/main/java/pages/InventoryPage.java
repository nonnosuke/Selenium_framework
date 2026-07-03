package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.network.Header;
import pages.components.FooterComponent;
import pages.components.HeaderComponent;

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

    public boolean loadedPage(){
        return "Products".equals(getText(pageTitle));
    }

    public void addProductToCart(String productName){
        By addBtn = By.xpath(
                "//div[contains(@class,'inventory_item')][.//div[text()='" + productName + "']]//button"
        );
        click(addBtn);
    }

    public int getCartCount(){
        return header.getCartCount();
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
