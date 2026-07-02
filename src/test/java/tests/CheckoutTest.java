package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.*;
import utils.DriverFactory;
import utils.ScreenshotWatcher;
import utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Swag Labs")
@Feature("Checkout")
@ExtendWith(ScreenshotWatcher.class)
public class CheckoutTest extends Base_Test{
    @Story("Input User Information")
    @DisplayName("Input User information to the form")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void inputUserInfo(){
        //Arrange (login)
        loginPage().login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);

        //Act
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        CartPage cartpage = inventoryPage.openCart();
        CheckoutPage checkoutpage = cartpage.checkout();
        CheckoutOverviewPage overviewpage = checkoutpage.enterInfo("Shohei", "Otani", "V6B 1V5");

        //Assert
        assertTrue(overviewpage.loadedPage());
        System.out.println(overviewpage.getCartItem());
    }

    @Story("Finish checkout")
    @DisplayName("Complete form and finish order")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    void finishOrder(){
        //Arrange (login)
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);

        //Act
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        CartPage cartpage = inventoryPage.openCart();
        CheckoutPage checkoutpage = cartpage.checkout();
        CheckoutOverviewPage overviewpage = checkoutpage.enterInfo("Shohei", "Otani", "V6B 1V5");
        CompleteOrderPage completeorderpage = overviewpage.finish();

        //Assert
        assertTrue(completeorderpage.loadedPage());
    }

    @Story("Cancel checkout")
    @DisplayName("After moving to checkout page, cancel checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void cancelCheckout(){
        //Arrange (login)
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);

        //Act
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        CartPage cartpage = inventoryPage.openCart();
        CheckoutPage checkoutpage = cartpage.checkout();
        CartPage returnedCartPage = checkoutpage.cancel();

        //Assert
        assertTrue(returnedCartPage.loadedPage());
    }

    @Story("User information required message")
    @DisplayName("Display error message")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void requireField(){
        //Arrange (login)
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);

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

    @Story("Cancel checkout from overview page")
    @DisplayName("Cancel checkout from overview")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void cancelCheckoutOverview(){
        //Arrange (login)
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);

        //Act
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        CartPage cartpage = inventoryPage.openCart();
        CheckoutPage checkoutpage = cartpage.checkout();
        CheckoutOverviewPage overviewpage = checkoutpage.enterInfo("Shohei", "Otani", "V6B 1V5");

        InventoryPage inventory = overviewpage.cancel();
        assertTrue(inventory.loadedPage());
    }
}
