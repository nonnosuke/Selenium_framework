package tests;

import io.qameta.allure.*;
import models.ProductData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.InventoryPage;
import pages.ProductDetailPage;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ScreenshotWatcher;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Swag Labs")
@Feature("Product detail page")
@ExtendWith(ScreenshotWatcher.class)
public class ProductDetailTest extends Base_Test{

    @Story("Open product detail")
    @DisplayName("Open product page")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#products")
    void openProduct(ProductData product){
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        InventoryPage inventory = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);

        ProductDetailPage detailPage = inventory.openProduct(product.productName());

        assertTrue(detailPage.loadedPage());
        assertEquals(product.productName(), detailPage.getProductName());
    }

    @Story("Back to product list")
    @DisplayName("Back to product page")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void backToProducts(){
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        InventoryPage inventory = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);

        ProductDetailPage detailPage = inventory.openProduct("Sauce Labs Backpack");

        InventoryPage inventoryPage = detailPage.backToProducts();

        assertTrue(inventoryPage.loadedPage());
    }

    @Story("Add to cart from detail page")
    @DisplayName("Add to cart from product page")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void addProductFromDetailPage(){
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        InventoryPage inventory = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);

        ProductDetailPage detailPage = inventory.openProduct("Sauce Labs Backpack");
        detailPage.addToCart();

        assertEquals(1, detailPage.getCartCount());
    }

    @Story("Remove from cart from detail page")
    @DisplayName("Remove from cart from product page")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void removeProductFromDetailPage(){
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        InventoryPage inventory = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);

        ProductDetailPage detailPage = inventory.openProduct("Sauce Labs Backpack");
        detailPage.addToCart();
        detailPage.removeFromCart();

        assertTrue(detailPage.addBtnDisplayed());
        assertFalse(detailPage.hasCartBadge());
    }
}
