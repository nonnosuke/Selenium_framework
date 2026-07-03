package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.network.Header;
import pages.components.HeaderComponent;

public class InventoryPage extends BasePage {

    public final HeaderComponent header;

    public InventoryPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
        this.header = new HeaderComponent(driver, timeoutSeconds);
    }

    public HeaderComponent header(){
        return header;
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
