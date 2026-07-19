package tests.users;

import assertions.HeaderAssertions;
import assertions.InventoryAssertions;
import assertions.ProductDetailAssertions;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.LoginData;
import models.ProductData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.InventoryPage;
import pages.ProductDetailPage;
import tests.Base_Test;
import utils.CsvDataProvider;


@Epic("Swag Labs")
@Feature("Visual User")
public class VisualUserTest extends Base_Test {

    @Test
    void inventoryPageLoaded(){
        LoginData user = CsvDataProvider.getUser("visual_user");
        InventoryPage inventoryPage = login(user);

        InventoryAssertions.assertLoaded(inventoryPage);
    }

    @Story("Inventory Images")
    @Test
    void productImageIsIncorrect(){
        ProductData backPack = CsvDataProvider.getProduct("Sauce Labs Backpack");
        LoginData user = CsvDataProvider.getUser("visual_user");
        InventoryPage inventoryPage = login(user);

        InventoryAssertions.assertIncorrectProductImage(inventoryPage, backPack);
    }

    @Story("Product Detail Images")
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#products")
    void detailImageShouldBeCorrect(ProductData product){
        LoginData user = CsvDataProvider.getUser("visual_user");
        ProductDetailPage detailPage = login(user)
                .openProduct(product.productName());

        ProductDetailAssertions.assertProductImage(detailPage, product);
    }

    @Story("Add button layout")
    @Test
    void addBtnIsMisaligned(){
        ProductData tShirts = CsvDataProvider.getProduct("Test.allTheThings() T-Shirt (Red)");
        LoginData user = CsvDataProvider.getUser("visual_user");
        InventoryPage inventoryPage = login(user);

        InventoryAssertions.assertAddBtnMisaligned(inventoryPage, tShirts);
    }

    @Story("Product name layout")
    @Test
    void productNameIsRightAligned(){
        ProductData boltTshirt = CsvDataProvider.getProduct("Sauce Labs Bolt T-Shirt");
        ProductData jacket = CsvDataProvider.getProduct("Sauce Labs Fleece Jacket");

        LoginData user = CsvDataProvider.getUser("visual_user");
        InventoryPage inventoryPage = login(user);

        InventoryAssertions.assertProductNameRightAligned(inventoryPage, boltTshirt);
        InventoryAssertions.assertProductNameRightAligned(inventoryPage, jacket);
    }

    @Story("Prices")
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#products")
    void pricesAreIncorrect(ProductData product){
        LoginData user = CsvDataProvider.getUser("visual_user");
        InventoryPage inventoryPage = login(user);

        //Assert
        //assertNotEquals(product.price(), actual);
        InventoryAssertions.assertIncorrectPrice(inventoryPage, product);
    }

    @Story("Cart icon layout")
    @Test
    void cartIconIsMisplaced(){
        LoginData user = CsvDataProvider.getUser("visual_user");
        InventoryPage inventoryPage = login(user)
                .addProductToCart("Sauce Labs Backpack");

        HeaderAssertions.assertCartIconMisaligned(inventoryPage);
    }

    @Story("Menu icon layout")
    @Test
    void menuIconIsRotated(){
        LoginData user = CsvDataProvider.getUser("visual_user");
        InventoryPage inventoryPage = login(user);

        HeaderAssertions.assertMenuIconRotated(inventoryPage);
    }
}
