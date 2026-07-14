package pages.components;

import base.BasePage;
import locators.CartLocators;
import models.CartItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartItemsComponent extends BasePage {


    public CartItemsComponent(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    public List<CartItem> getItems(){
        List<WebElement> elements = elements(CartLocators.CART_ITEM);
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

    public boolean hasProduct(String productName){
        return getItems().stream()
                .anyMatch(item -> item.getName().equals(productName));
    }

    //Number of product types
    public int getItemCount(){
        return getItems().size();
    }
}
