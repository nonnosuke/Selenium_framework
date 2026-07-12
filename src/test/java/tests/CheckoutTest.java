package tests;

import assertions.CartAssertions;
import assertions.CheckoutAssertions;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;
import assertions.InventoryAssertions;
import utils.CheckoutDataFactory;


@Epic("Swag Labs")
@Feature("Checkout")
public class CheckoutTest extends Base_Test{
    @Story("Input User Information")
    @DisplayName("Input User information to the form")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void inputUserInfo(){
        CheckoutOverviewPage overviewPage =
                loginAndOpenCheckoutPage("Sauce Labs Backpack")
                .fill(CheckoutDataFactory.valid())
                .continueBtn();

        //Assert
        CheckoutAssertions.assertOverviewLoaded(overviewPage);
    }

    @Story("Finish checkout")
    @DisplayName("Complete form and finish order")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    void finishOrder(){
        CompleteOrderPage completeOrderPage = loginAndOpenCheckoutPage("Sauce Labs Backpack")
                .fill(CheckoutDataFactory.valid())
                        .continueBtn()
                                .finish();

        //Assert
        CheckoutAssertions.assertCompleteLoaded(completeOrderPage);
    }

    @Story("Cancel checkout")
    @DisplayName("After moving to checkout page, cancel checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void cancelCheckout(){
        CartPage cartPage = loginAndOpenCheckoutPage("Sauce Labs Backpack")
                .cancel();

        //Assert
        CartAssertions.assertLoaded(cartPage);
    }

    @Story("User information required message")
    @DisplayName("Display error message")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void requireField(){
        CheckoutPage checkoutPage = loginAndOpenCheckoutPage("Sauce Labs Backpack")
                .fill(CheckoutDataFactory.withoutPostalCode());
        checkoutPage.continueBtn();

        //Assert
        //CheckoutAssertions.assertError(checkoutPage, "Error: First Name is required");
        //CheckoutAssertions.assertError(checkoutPage, "Error: Last Name is required");
        CheckoutAssertions.assertError(checkoutPage, "Error: Postal Code is required");
    }

    @Story("Cancel checkout from overview page")
    @DisplayName("Cancel checkout from overview")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void cancelCheckoutOverview(){
        CheckoutOverviewPage overviewPage = loginAndOpenCheckoutPage("Sauce Labs Backpack")
                .fill(CheckoutDataFactory.valid())
                .continueBtn();

        InventoryPage inventoryPage = overviewPage.cancel();

        //Assert
        InventoryAssertions.assertLoaded(inventoryPage);
    }
}
