package tests.users;

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
import pages.InventoryPage;
import pages.ProductDetailPage;
import tests.Base_Test;
import utils.CsvDataProvider;

@Epic("Swag Labs")
@Feature("Problem User")
public class ProblemUserTest extends Base_Test {

    @Story("Wrong product image")
    @DisplayName("Problem user shows wrong products' images")
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#products")
    void showsIncorrectImage(ProductData product){
        LoginData user = CsvDataProvider.getUser("problem_user");
        InventoryPage inventoryPage = login(user);

        //Assert
        InventoryAssertions.assertIncorrectProductImage(inventoryPage, product);
    }

    @Story("Wrong product detail")
    @DisplayName("Problem user opens wrong product page")
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#products")
    void opensIncorrectProduct(ProductData product){
        LoginData user = CsvDataProvider.getUser("problem_user");

        ProductDetailPage detailPage = login(user).openProduct(product.productName());

        //Assert
        ProductDetailAssertions.assertIncorrectProductName(detailPage, product);
    }

    @Story("Wrong product description")
    @DisplayName("Problem user opens wrong product page and description")
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#products")
    void showsIncorrectDescription(ProductData product){
        LoginData user = CsvDataProvider.getUser("problem_user");
        ProductDetailPage detailPage = login(user).openProduct(product.productName());

        //Assert
        ProductDetailAssertions.assertIncorrectDescription(detailPage, product);
    }

    @Story("Wrong product price in detail page")
    @DisplayName("Problem user opens wrong product page and price")
    @Test
    void showsIncorrectPrice(){
        LoginData user = CsvDataProvider.getUser("problem_user");
        ProductData jacket = CsvDataProvider.getProduct("Sauce Labs Fleece Jacket");
        ProductDetailPage detailPage = login(user).openProduct(jacket.productName());

        //Assert
        ProductDetailAssertions.assertIncorrectPrice(detailPage, String.valueOf(jacket.price()));
    }
}
