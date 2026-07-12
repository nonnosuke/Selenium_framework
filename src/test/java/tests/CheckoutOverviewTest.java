package tests;

import assertions.CheckoutAssertions;
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
        CheckoutOverviewPage overviewPage = loginAndOpenCheckoutPage("Sauce Labs Backpack")
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
        CheckoutOverviewAssertions.assertInventoryLoaded(inventoryPage);
    }

    @Test
    void verifyCartItem(){
        CheckoutOverviewPage overviewPage = loginAndOpenCheckoutPage("Sauce Labs Backpack")
                .fill(CheckoutDataFactory.valid())
                .continueBtn();

        CheckoutOverviewAssertions.assertCartItems(overviewPage, 1);
    }

    @Test
    void verifyItemTotal(){
        ProductData backpack = CsvDataProvider.getProduct("Sauce Labs Backpack");

        CheckoutOverviewPage overviewPage = loginAndOpenCheckoutPage("Sauce Labs Backpack")
                .fill(CheckoutDataFactory.valid())
                .continueBtn();

        double itemTotal = PriceCalculator.itemTotal(backpack.price());

        CheckoutOverviewAssertions.assertItemTotal(overviewPage, itemTotal);
    }

    @Test
    void verifyTax(){
        ProductData backpack = CsvDataProvider.getProduct("Sauce Labs Backpack");

        CheckoutOverviewPage overviewPage = loginAndOpenCheckoutPage("Sauce Labs Backpack")
                .fill(CheckoutDataFactory.valid())
                .continueBtn();

        double itemTotal = PriceCalculator.itemTotal(backpack.price());
        double tax = PriceCalculator.tax(itemTotal);

        CheckoutOverviewAssertions.assertTax(overviewPage, tax);
    }

    @Test
    void verifyTotal(){
        ProductData backpack = CsvDataProvider.getProduct("Sauce Labs Backpack");

        CheckoutOverviewPage overviewPage = loginAndOpenCheckoutPage("Sauce Labs Backpack")
                .fill(CheckoutDataFactory.valid())
                .continueBtn();

        double itemTotal = PriceCalculator.itemTotal(backpack.price());
        double total = PriceCalculator.total(itemTotal);

        CheckoutOverviewAssertions.assertTotal(overviewPage, total);
    }
}
