package tests;

import base.BasePage;
import org.junit.jupiter.api.Test;
import pages.*;
import utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompleteOrderTest extends Base_Test {

    @Test
    void completeOrder(){
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
        assertEquals("Thank you for your order!", completeorderpage.getText());
    }

    @Test
    void backToHome(){
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
        InventoryPage inventoryPageAfterBackHome = completeorderpage.backHome();

        //Assert
        assertTrue(inventoryPageAfterBackHome.loadedPage());
    }

}
