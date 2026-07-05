package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.*;
import utils.ScreenshotWatcher;

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
        //Act
        CheckoutPage checkoutPage = loginAndOpenCheckoutPage("Sauce Labs Backpack");

        CheckoutOverviewPage overviewpage = checkoutPage.enterInfo("Shohei", "Otani", "V6B 1V5");

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
        //Act
        CheckoutPage checkoutPage = loginAndOpenCheckoutPage("Sauce Labs Backpack");

        CheckoutOverviewPage overviewpage = checkoutPage.enterInfo("Shohei", "Otani", "V6B 1V5");
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
        //Act
        CheckoutPage checkoutPage = loginAndOpenCheckoutPage("Sauce Labs Backpack");
        CartPage returnedCartPage = checkoutPage.cancel();

        //Assert
        assertTrue(returnedCartPage.loadedPage());
    }

    @Story("User information required message")
    @DisplayName("Display error message")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void requireField(){
        //Arrange (login)
        CartPage cartPage = loginAndOpenCartPage("Sauce Labs Backpack");

        CheckoutPage checkoutpage = cartPage.checkout();
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
        CheckoutPage checkoutPage = loginAndOpenCheckoutPage("Sauce Labs Backpack");

        CheckoutOverviewPage overviewpage = checkoutPage.enterInfo("Shohei", "Otani", "V6B 1V5");

        InventoryPage inventory = overviewpage.cancel();
        assertTrue(inventory.loadedPage());
    }
}
