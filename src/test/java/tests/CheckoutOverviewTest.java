package tests;

import assertions.CheckoutOverviewAssertions;
import assertions.InventoryAssertions;
import io.qameta.allure.*;
import models.ProductData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CheckoutOverviewPage;
import pages.CompleteOrderPage;
import pages.InventoryPage;
import utils.CheckoutDataFactory;
import utils.CsvDataProvider;
import utils.PriceCalculator;

@Epic("Swag Labs")
@Feature("Checkout Overview")
public class CheckoutOverviewTest extends Base_Test{
    @Story("Finish checkout")
    @DisplayName("Complete form and finish order")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    void finishOrder(){
        ProductData backpack =
                CsvDataProvider.getProduct("Sauce Labs Backpack");
        CheckoutOverviewPage overviewPage = loginAndOpenCheckoutPage(backpack.productName())
                .fill(CheckoutDataFactory.valid())
                .continueBtn();
        CompleteOrderPage completeOrderPage = overviewPage.finish();

        //Assert
        CheckoutOverviewAssertions.assertCompleteLoaded(completeOrderPage);
    }

    @Story("Cancel checkout from overview page")
    @DisplayName("Cancel checkout from overview")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void cancelCheckoutOverview(){
        ProductData backpack =
                CsvDataProvider.getProduct("Sauce Labs Backpack");

        CheckoutOverviewPage overviewPage = loginAndOpenCheckoutPage(backpack.productName())
                .fill(CheckoutDataFactory.valid())
                .continueBtn();

        InventoryPage inventoryPage = overviewPage.cancel();

        //Assert
        InventoryAssertions.assertLoaded(inventoryPage);
    }

    @Story("Verify number of items in a cart")
    @DisplayName("Verify cart item number")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void verifyCartItem(){
        ProductData backpack = CsvDataProvider.getProduct("Sauce Labs Backpack");
        CheckoutOverviewPage overviewPage = loginAndOpenCheckoutPage(backpack.productName())
                .fill(CheckoutDataFactory.valid())
                .continueBtn();

        //Assert
        CheckoutOverviewAssertions.assertItemCount(overviewPage, 1);
    }

    @Story("Verify items total price without tax")
    @DisplayName("Verify total price without tax")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void verifySubTotal(){
        ProductData backpack = CsvDataProvider.getProduct("Sauce Labs Backpack");

        CheckoutOverviewPage overviewPage = loginAndOpenCheckoutPage(backpack.productName())
                .fill(CheckoutDataFactory.valid())
                .continueBtn();

        double itemTotal = PriceCalculator.itemTotal(backpack.price());

        //Assert
        CheckoutOverviewAssertions.assertItemSubtotal(overviewPage, itemTotal);
    }

    @Story("Verify items total tax")
    @DisplayName("Verify total tax")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void verifyTax(){
        ProductData backpack = CsvDataProvider.getProduct("Sauce Labs Backpack");

        CheckoutOverviewPage overviewPage = loginAndOpenCheckoutPage(backpack.productName())
                .fill(CheckoutDataFactory.valid())
                .continueBtn();

        double itemTotal = PriceCalculator.itemTotal(backpack.price());
        double tax = PriceCalculator.tax(itemTotal);

        //Assert
        CheckoutOverviewAssertions.assertTax(overviewPage, tax);
    }

    @Story("Verify items total price with tax")
    @DisplayName("Verify total price with tax")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void verifyTotal(){
        ProductData backpack = CsvDataProvider.getProduct("Sauce Labs Backpack");

        CheckoutOverviewPage overviewPage = loginAndOpenCheckoutPage(backpack.productName())
                .fill(CheckoutDataFactory.valid())
                .continueBtn();

        double itemTotal = PriceCalculator.itemTotal(backpack.price());
        double total = PriceCalculator.total(itemTotal);

        //Assert
        CheckoutOverviewAssertions.assertTotal(overviewPage, total);
    }
}
