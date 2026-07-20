package tests.users;

import assertions.CartAssertions;
import assertions.CheckoutOverviewAssertions;
import assertions.InventoryAssertions;
import assertions.ProductDetailAssertions;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.LoginData;
import models.ProductData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.*;
import tests.Base_Test;
import utils.CheckoutDataFactory;
import utils.CsvDataProvider;

@Epic("Swag Labs")
@Feature("Error User")
public class ErrorUserTest extends Base_Test {

    @Story("Add button is broken")
    @DisplayName("Add button cannot add selected products")
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#brokenAddProducts")
    void addToCartDoesNotWork(ProductData product){
        LoginData user = CsvDataProvider.getUser("error_user");

        CartPage cartPage = login(user)
                .addProductToCart(product.productName())
                .openCart();

        CartAssertions.assertNotHaveProduct(cartPage, (product.productName()));
    }

    @Story("Remove button is broken")
    @DisplayName("Remove button remains displayed after clicking")
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#brokenRemoveProducts")
    void removeProductDoesNotWork(ProductData product) {
        LoginData user = CsvDataProvider.getUser("error_user");

        InventoryPage inventoryPage = login(user)
                .addProductToCart(product.productName())
                .removeProductFromCart(product.productName());

        InventoryAssertions.assertRemoveBtnStillDisplayed(inventoryPage, product);
    }

    @Story("Product description is missing")
    @DisplayName("Product description is missing")
    @Test
    void productDescriptionIsMissing(){
        LoginData user = CsvDataProvider.getUser("error_user");
        ProductData backpack = CsvDataProvider.getProduct("Sauce Labs Backpack");

        ProductDetailPage detailPage = login(user)
                .openProduct(backpack.productName());

        ProductDetailAssertions.assertDescriptionIsNotDisplayed(detailPage);
    }


    @Story("Checkout validation is skipped")
    @DisplayName("Checkout proceeds without last name")
    @Test
    void checkoutValidationIsSkipped(){
        LoginData user = CsvDataProvider.getUser("error_user");
        CheckoutOverviewPage overviewPage = login(user)
                .addProductToCart("Sauce Labs Backpack")
                .openCart()
                .checkout()
                .fill(CheckoutDataFactory.withoutLastName())
                .continueBtn();

        CheckoutOverviewAssertions.assertLoaded(overviewPage);
    }
}
