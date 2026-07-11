package tests;

import assertions.CartAssertions;
import assertions.CheckoutAssertions;
import io.qameta.allure.*;
import models.CartItem;
import models.ProductData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import assertions.InventoryAssertions;


import static org.junit.jupiter.api.Assertions.*;

@Epic("Swag Labs")
@Feature("Cart")
public class CartTest extends Base_Test{

    @Story("Remove item from cart")
    @DisplayName("Remove button")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void removeItemfromCart(){
        //Arrange
        CartPage cartPage = loginAndOpenCartPage("Sauce Labs Backpack",
                "Sauce Labs Bike Light")
                .removeProduct("Sauce Labs Backpack");

        //Assert
        CartAssertions.assertItemCount(cartPage, 1);
        CartAssertions.assertNotHaveProduct(cartPage, "Sauce Labs Backpack");
        //check cart badge function
        //CartAssertions.assertCartCount(cartPage,2);
    }

    @Story("Checkout items")
    @DisplayName("Checkout button")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void checkout(){
        //Arrange
        CheckoutPage checkoutPage = loginAndOpenCartPage("Sauce Labs Backpack")
                .checkout();

        //Assert
        CheckoutAssertions.assertCheckoutLoaded(checkoutPage);
    }

    @Story("Continue shopping")
    @DisplayName("Continue shopping button after open cart")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void continueShopping(){
        //Arrange
        CartPage cartPage = loginAndOpenCartPage("Sauce Labs Backpack");

        //Act
        InventoryPage inventoryPage = cartPage.continueShopping();

        //Assert
        InventoryAssertions.assertLoaded(inventoryPage);
    }
}
