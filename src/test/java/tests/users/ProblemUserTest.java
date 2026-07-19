package tests.users;

import assertions.InventoryAssertions;
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

import static org.junit.jupiter.api.Assertions.*;

@Epic("Swag Labs")
@Feature("Problem User")
public class ProblemUserTest extends Base_Test {

    @Story("Login as problem user")
    @DisplayName("Problem user log in")
    @Test
    void loginAsProblemUser(){
        LoginData user = CsvDataProvider.getUser("problem_user");
        InventoryPage inventoryPage = login(user);

        //Assert
        InventoryAssertions.assertLoaded(inventoryPage);
    }

    @Story("Wrong product image")
    @DisplayName("Problem user shows wrong products' images")
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#products")
    void wrongImage(ProductData product){
        LoginData user = CsvDataProvider.getUser("problem_user");
        InventoryPage inventoryPage = login(user);

        //Assert
        InventoryAssertions.assertIncorrectProductImage(inventoryPage, product);
    }

    @Story("Wrong product detail")
    @DisplayName("Problem user opens wrong product page")
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#products")
    void wrongProductDetail(ProductData product){
        LoginData user = CsvDataProvider.getUser("problem_user");
        //ProductData backpack = CsvDataProvider.getProduct("Sauce Labs Backpack");

        ProductDetailPage detailPage = login(user).openProduct(product.productName());

        //Assert
        //ProductDetailAssertions.assertProductName(detailPage, product.productName());
        assertNotEquals(product.productName(), detailPage.getProductName());
    }

    @Story("Wrong product description")
    @DisplayName("Problem user opens wrong product page and description")
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#products")
    void wrongDescription(ProductData product){
        LoginData user = CsvDataProvider.getUser("problem_user");
        ProductDetailPage detailPage = login(user).openProduct(product.productName());

        //Assert
        //ProductDetailAssertions.assertDescription(detailPage, product.description());
        assertNotEquals(product.description(), detailPage.getDescription());
    }

    @Story("Wrong product price in detail page")
    @DisplayName("Problem user opens wrong product page and price")
//    @ParameterizedTest
//    @MethodSource("utils.CsvDataProvider#products")
    @Test
    void wrongPrice(){
        LoginData user = CsvDataProvider.getUser("problem_user");
        ProductData backpack = CsvDataProvider.getProduct("Sauce Labs Backpack");
        ProductDetailPage detailPage = login(user).openProduct(backpack.productName());

        //Assert
        //ProductDetailAssertions.assertIncorrectPrice(detailPage, backpack.price());
        assertNotEquals("$" + backpack.price(), detailPage.getPrice());
    }
}
