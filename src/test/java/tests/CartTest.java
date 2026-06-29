package tests;

import models.CartItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import util.ScreenshotWatcher;
import utils.ConfigReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ScreenshotWatcher.class)
public class CartTest extends Base_Test{
    @Test
    void addItemtoCart(){
        //Arrange (login)
        InventoryPage inventoryPage = loginPage().login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        //Act
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        CartPage cartpage = inventoryPage.openCart();

        //Assert
        assertEquals(1, inventoryPage.getCartCount());
        assertTrue(cartpage.hasProduct("Sauce Labs Backpack"));
    }

    @Test
    void removeItemfromCart(){
        //Arrange
        InventoryPage inventoryPage = loginPage().login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.addProductToCart("Sauce Labs Bike Light");

        //Act
        CartPage cartpage = inventoryPage.openCart();
        cartpage.removeProduct("Sauce Labs Backpack");

        List<CartItem> items = cartpage.getCartItems();

        //Assert
        assertEquals(1, items.size());
        assertFalse(items.stream().anyMatch(item -> item.getName().equals("Sauce Labs Backpack")));
        //check cart badge function
        assertEquals(1, cartpage.getCartcount());
    }

    @Test
    void checkout(){
        //Arrange
        InventoryPage inventoryPage = loginPage().login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );
        inventoryPage.addProductToCart("Sauce Labs Backpack");

        //Act
        CartPage cartpage = inventoryPage.openCart();
        CheckoutPage checkoutpage = cartpage.checkout();

        //Assert
        assertTrue(checkoutpage.loadedPage());
    }

    @Test
    void continueShopping(){
        //Arrange
        InventoryPage inventoryPage = loginPage().login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );
        inventoryPage.addProductToCart("Sauce Labs Backpack");

        //Act
        CartPage cartpage = inventoryPage.openCart();
        InventoryPage inventorypage = cartpage.continueShopping();

        //Assert
        assertTrue(inventorypage.loadedPage());
    }
}
