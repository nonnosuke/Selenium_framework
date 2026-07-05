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
import utils.ScreenshotWatcher;

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
        //Act
        CartPage cartpage = loginAndOpenCartPage(product.productName());
        //inventoryPage.addProductToCart("Sauce Labs Backpack");

        //Assert
        assertEquals(1, cartpage.getCartCount());
        assertTrue(cartpage.hasProduct(product.productName()));
    }

    @Story("Remove item from cart")
    @DisplayName("Remove button")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void removeItemfromCart(){
        //Arrange
        CartPage cartPage = loginAndOpenCartPage("Sauce Labs Backpack",
                "Sauce Labs Bike Light");

        //Act
        cartPage.removeProduct("Sauce Labs Backpack");

        List<CartItem> items = cartPage.getCartItems();

        //Assert
        assertEquals(1, items.size());
        assertFalse(items.stream().anyMatch(item -> item.getName().equals("Sauce Labs Backpack")));
        //check cart badge function
        assertEquals(1, cartPage.getCartCount());
    }

    @Story("Checkout items")
    @DisplayName("Checkout button")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void checkout(){
        //Arrange
        CartPage cartPage = loginAndOpenCartPage("Sauce Labs Backpack");

        //Act
        CheckoutPage checkoutpage = cartPage.checkout();

        //Assert
        assertTrue(checkoutpage.loadedPage());
    }

    @Story("Continue shopping")
    @DisplayName("Continue shopping button after open cart")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void continueShopping(){
        //Arrange
        CartPage cartPage = loginAndOpenCartPage("Sauce Labs Backpack");

        //Act
        InventoryPage inventorypage = cartPage.continueShopping();

        //Assert
        assertTrue(inventorypage.loadedPage());
    }
}
