package tests;

import assertions.CompleteOrderAssertions;
import assertions.InventoryAssertions;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Swag Labs")
@Feature("Complete order")
public class CompleteOrderTest extends Base_Test {

    @Story("Complete order")
    @DisplayName("Complete order and display complete message")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    void completeOrder(){
        //Arrange (login)
        CartPage cartPage = loginAndOpenCartPage("Sauce Labs Backpack");

        CheckoutPage checkoutPage = cartPage.checkout();
        CheckoutOverviewPage overviewPage = checkoutPage.enterInfo("Shohei", "Otani", "V6B 1V5");
        CompleteOrderPage completeorderPage = overviewPage.finish();

        //Assert
        CompleteOrderAssertions.assertCompleteMessage(completeorderPage,"Thank you for your order!");
    }

    @Story("Back to home button")
    @DisplayName("After complete order, back to home")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void backToHome(){
        //Arrange (login)
        CartPage cartPage = loginAndOpenCartPage("Sauce Labs Backpack");

        CheckoutPage checkoutpage = cartPage.checkout();
        CheckoutOverviewPage overviewpage = checkoutpage.enterInfo("Shohei", "Otani", "V6B 1V5");
        CompleteOrderPage completeorderpage = overviewpage.finish();
        InventoryPage inventoryPageAfterBackHome = completeorderpage.backHome();

        //Assert
        CompleteOrderAssertions.assertInventoryLoaded(inventoryPageAfterBackHome);
    }

}
