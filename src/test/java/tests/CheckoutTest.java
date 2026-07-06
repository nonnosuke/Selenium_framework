package tests;

import assertions.CartAssertions;
import assertions.CheckoutAssertions;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;
import assertions.InventoryAssertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Swag Labs")
@Feature("Checkout")
public class CheckoutTest extends Base_Test{
    @Story("Input User Information")
    @DisplayName("Input User information to the form")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void inputUserInfo(){
        //Arrange (login)
        //Act
        CheckoutPage checkoutPage = loginAndOpenCheckoutPage("Sauce Labs Backpack");

        CheckoutOverviewPage overviewPage = checkoutPage.enterInfo("Shohei", "Otani", "V6B 1V5");

        //Assert
        CheckoutAssertions.assertOverviewLoaded(overviewPage);
        System.out.println(overviewPage.getCartItem());
    }

    @Story("Finish checkout")
    @DisplayName("Complete form and finish order")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    void finishOrder(){
        //Arrange (login)
        //Act
        CheckoutPage checkoutPage = loginAndOpenCheckoutPage("Sauce Labs Backpack");

        CheckoutOverviewPage overviewPage = checkoutPage.enterInfo("Shohei", "Otani", "V6B 1V5");
        CompleteOrderPage completeorderPage = overviewPage.finish();

        //Assert
        CheckoutAssertions.assertCompleteLoaded(completeorderPage);
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
        CartAssertions.assertLoaded(returnedCartPage);
    }

    @Story("User information required message")
    @DisplayName("Display error message")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void requireField(){
        //Arrange (login)
        CartPage cartPage = loginAndOpenCartPage("Sauce Labs Backpack");

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.enterFirstName("Shohei");
        checkoutPage.enterLastName("Otani");
        checkoutPage.enterPostalCode("");
        checkoutPage.continueBtn();


        //Assert
        //assertEquals("Error: First Name is required", checkoutpage.getErrorMessage());
        //assertEquals("Error: Last Name is required", checkoutpage.getErrorMessage());
        CheckoutAssertions.assertError(checkoutPage, "Error: Postal Code is required");
    }

    @Story("Cancel checkout from overview page")
    @DisplayName("Cancel checkout from overview")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void cancelCheckoutOverview(){
        //Arrange (login)
        CheckoutPage checkoutPage = loginAndOpenCheckoutPage("Sauce Labs Backpack");

        CheckoutOverviewPage overviewPage = checkoutPage.enterInfo("Shohei", "Otani", "V6B 1V5");

        InventoryPage inventoryPage = overviewPage.cancel();
        InventoryAssertions.assertLoaded(inventoryPage);
    }
}
