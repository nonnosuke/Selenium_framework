package pages;

import base.BasePage;
import models.CartItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private final By pageTitle = By.className("title");
    private final By checkoutBtn = By.id("checkout");
    private final By continueShoppingBtn = By.id("continue-shopping");
    private final By cartItem = By.className("cart_item");


    public CartPage (WebDriver driver, int timeoutSeconds){
        super(driver, timeoutSeconds);
    }

    public boolean loadedPage(){
        return textOf(pageTitle).equals("Your Cart");
    }

    public boolean hasProduct(String productName){
        By product = By.xpath("//div[text()='" + productName + "']");
        //return visible(product).isDisplayed();
        return !driver.findElements(product).isEmpty();
    }

    public List<CartItem> getCartItems(){
        List<WebElement> elements = driver.findElements(cartItem);
        List<CartItem> items = new ArrayList<>();

        for (WebElement element : elements) {
            String name = element
                    .findElement(By.className("inventory_item_name"))
                    .getText();

            String price = element
                    .findElement(By.className("inventory_item_price"))
                    .getText();

            int quantity = Integer.parseInt(element
                    .findElement(By.className("cart_quantity"))
                    .getText());

            items.add(new CartItem(name, price, quantity));
        }
        return items;
    }

    public CheckoutPage checkout(){
        click(checkoutBtn);
        return new CheckoutPage(driver, timeoutSeconds);
    }

    public InventoryPage continueShopping(){
        click(continueShoppingBtn);
        return new InventoryPage(driver, timeoutSeconds);
    }

    public void removeProduct(String productName){
        By removeBtn = By.xpath(
                "//div[@class='cart_item'][.//div[text()='" + productName + "']]//button"
        );
        click(removeBtn);
    }

    public int getCartcount(){
        By cartBadge = By.className("shopping_cart_badge");
        return Integer.parseInt(textOf(cartBadge));
    }
}
