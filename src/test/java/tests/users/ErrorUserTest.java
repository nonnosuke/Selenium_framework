package tests.users;

import assertions.CheckoutOverviewAssertions;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import models.LoginData;
import models.ProductData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.*;
import tests.Base_Test;
import utils.CheckoutDataFactory;
import utils.CsvDataProvider;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Swag Labs")
@Feature("Error User")
public class ErrorUserTest extends Base_Test {

    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#brokenAddProducts")
    void addToCartNotWork(ProductData product){
        LoginData user = CsvDataProvider.getUser("error_user");

        CartPage cartPage = login(user)
                .addProductToCart(product.productName())
                .openCart();

        //CartAssertions.assertHasProduct(cartPage, (product.productName()));
        assertFalse(cartPage.cartItems().hasProduct(product.productName()));
    }

    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#brokenRemoveProducts")
    void removeProductNotWork(ProductData product){
        LoginData user = CsvDataProvider.getUser("error_user");

        CartPage cartPage = login(user)
                .addProductToCart(product.productName())
                        .openCart()
                                .removeProduct(product.productName());

        assertFalse(cartPage.cartItems().hasProduct(product.productName()));
    }

    @Test
    void productDescriptionError(){
        LoginData user = CsvDataProvider.getUser("error_user");
        ProductData backpack = CsvDataProvider.getProduct("Sauce Labs Backpack");

        ProductDetailPage detailPage = login(user)
                .openProduct(backpack.productName());

        assertFalse(detailPage.hasDescription());
    }


    @Test
    void checkoutValidationError(){
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
