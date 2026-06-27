package tests;

import org.junit.jupiter.api.Test;
import pages.*;
import utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTest extends Base_Test{
    @Test
    void inputUserInfo(){
        //Arrange (login)
        InventoryPage inventoryPage = loginPage().login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        //Act
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        CartPage cartpage = inventoryPage.openCart();
        CheckoutPage checkoutpage = cartpage.checkout();
        CheckoutOverviewPage overviewpage = checkoutpage.enterInfo("Shohei", "Otani", "V6B 1V5");

        //Assert
        assertTrue(overviewpage.loadedPage());
        System.out.println(overviewpage.getCartItem());
    }

    @Test
    void finishOrder(){
        //Arrange (login)
        InventoryPage inventoryPage = loginPage().login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        //Act
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        CartPage cartpage = inventoryPage.openCart();
        CheckoutPage checkoutpage = cartpage.checkout();
        CheckoutOverviewPage overviewpage = checkoutpage.enterInfo("Shohei", "Otani", "V6B 1V5");
        CompleteOrderPage completeorderpage = overviewpage.finish();

        //Assert
        assertTrue(completeorderpage.loadedPage());
    }

    @Test
    void cancelCheckout(){
        //Arrange (login)
        InventoryPage inventoryPage = loginPage().login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        //Act
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        CartPage cartpage = inventoryPage.openCart();
        CheckoutPage checkoutpage = cartpage.checkout();
        CartPage returnedCartPage = checkoutpage.cancel();

        //Assert
        System.out.println(driver.getCurrentUrl());
        assertTrue(returnedCartPage.loadedPage());
    }

    @Test
    void requireField(){
        //Arrange (login)
        InventoryPage inventoryPage = loginPage().login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        //Act
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        CartPage cartpage = inventoryPage.openCart();
        CheckoutPage checkoutpage = cartpage.checkout();
        checkoutpage.enterFirstName("Shohei");
        checkoutpage.enterLastName("Otani");
        checkoutpage.enterPostalCode("");
        checkoutpage.continueBtn();


        //Assert
        //assertEquals("Error: First Name is required", checkoutpage.getErrorMessage());
        //assertEquals("Error: Last Name is required", checkoutpage.getErrorMessage());
        assertEquals("Error: Postal Code is required", checkoutpage.getErrorMessage());
    }
}
