package tests;

import io.qameta.allure.*;
import models.CartItem;
import models.ProductData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import utils.DriverFactory;
import utils.ScreenshotWatcher;
import utils.ConfigReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Swag Labs")
@Feature("Cart")
@ExtendWith(ScreenshotWatcher.class)
public class CartTest extends Base_Test{
    @Story("Add product to cart")
    @DisplayName("Add item to cart")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#products")
    void addItemtoCart(ProductData product){
        //Arrange (login)
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);

        //Act
        inventoryPage.addProductToCart(product.productName());
        CartPage cartpage = inventoryPage.openCart();
        //inventoryPage.addProductToCart("Sauce Labs Backpack");

        //Assert
        assertEquals(1, inventoryPage.getCartCount());
        assertTrue(cartpage.hasProduct(product.productName()));
    }

    @Story("Remove item from cart")
    @DisplayName("Remove button")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void removeItemfromCart(){
        //Arrange
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);

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

    @Story("Checkout items")
    @DisplayName("Checkout button")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void checkout(){
        //Arrange
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);

        inventoryPage.addProductToCart("Sauce Labs Backpack");

        //Act
        CartPage cartpage = inventoryPage.openCart();
        CheckoutPage checkoutpage = cartpage.checkout();

        //Assert
        assertTrue(checkoutpage.loadedPage());
    }

    @Story("Continue shopping")
    @DisplayName("Continue shopping button after open cart")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void continueShopping(){
        //Arrange
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);

        inventoryPage.addProductToCart("Sauce Labs Backpack");

        //Act
        CartPage cartpage = inventoryPage.openCart();
        InventoryPage inventorypage = cartpage.continueShopping();

        //Assert
        assertTrue(inventorypage.loadedPage());
    }
}
