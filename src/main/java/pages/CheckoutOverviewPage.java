package pages;

import base.BasePage;
import models.CartItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    public CheckoutOverviewPage(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    private final By pageTitle = By.className("title");
    private final By cartItem = By.className("cart_item");
    private final By itemTotal = By.className("summary_subtotal_label");
    private final By tax = By.className("summary_tax_label");
    private final By total = By.className("summary_total_label");
    private final By finishBtn = By.id("finish");
    private final By cancelBtn = By.id("cancel");

    public boolean loadedPage(){
        return "Checkout: Overview".equals(getText(pageTitle));
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

    public double getItemTotal(){
        String text = getText(itemTotal);
        return Double.parseDouble(text.replace("Item total: $", ""));
    }

    public double getTax(){
        String text = getText(tax);
        return Double.parseDouble(text.replace("Tax: $", ""));
    }

    public double getTotal(){
        String text = getText(total);
        return Double.parseDouble(text.replace("Total: $", ""));
    }

    public CompleteOrderPage finish(){
        click(finishBtn);
        return new CompleteOrderPage(driver, timeoutSeconds);
    }

    public InventoryPage cancel(){
        click(cancelBtn);
        return new InventoryPage(driver, timeoutSeconds);
    }
}
