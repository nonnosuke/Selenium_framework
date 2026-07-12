package tests;

import assertions.CartAssertions;
import assertions.CheckoutAssertions;
import assertions.CheckoutOverviewAssertions;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;
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
        CheckoutOverviewAssertions.assertLoaded(overviewPage);
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

}
