package tests;

import assertions.InventoryAssertions;
import assertions.ProductDetailAssertions;
import io.qameta.allure.*;
import models.ProductData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.InventoryPage;
import pages.ProductDetailPage;

@Epic("Swag Labs")
@Feature("Product detail page")
public class ProductDetailTest extends Base_Test{

    @Story("Open product detail")
    @DisplayName("Open product detail page")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#products")
    void openProduct(ProductData product){
        ProductDetailPage detailPage = loginAndOpenProductPage(product.productName());

        ProductDetailAssertions.assertLoaded(detailPage);
    }

    @Story("Display product information")
    @DisplayName("Verify product information")
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#products")
    void verifyProductInfo(ProductData product){
        ProductDetailPage detailPage = loginAndOpenProductPage(product.productName());

        ProductDetailAssertions.assertProductName(detailPage, product.productName());
        ProductDetailAssertions.assertDescription(detailPage, product.description());
        ProductDetailAssertions.assertPrice(detailPage, product.price());
    }

    @Story("Back to product list")
    @DisplayName("Back to product page")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void backToProducts(){
        ProductDetailPage detailPage = loginAndOpenProductPage("Sauce Labs Backpack");

        InventoryPage inventoryPage = detailPage.backToProducts();

        InventoryAssertions.assertLoaded(inventoryPage);
    }

    @Story("Add to cart from detail page")
    @DisplayName("Add to cart from product page")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void addProductFromDetailPage(){
        ProductDetailPage detailPage = loginAndOpenProductPage("Sauce Labs Backpack");
        detailPage.addToCart();

        ProductDetailAssertions.assertCartBadgeVisible(detailPage);
        ProductDetailAssertions.assertCartCount(detailPage, 1);
        ProductDetailAssertions.assertRemoveBtnVisible(detailPage);
    }

    @Story("Remove from cart from detail page")
    @DisplayName("Remove from cart from product page")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void removeProductFromDetailPage(){
        ProductDetailPage detailPage = loginAndOpenProductPage("Sauce Labs Backpack");
        detailPage.addToCart();
        detailPage.removeFromCart();

        ProductDetailAssertions.assertCartBadgeHidden(detailPage);
        ProductDetailAssertions.assertAddBtnVisible(detailPage);
    }

    @Story("Cart badge count")
    @DisplayName("Cart badge count")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void cartBadgeCount(){
        ProductDetailPage detailPage = loginAndOpenProductPage("Sauce Labs Backpack");
        detailPage.addToCart();

        ProductDetailAssertions.assertCartCount(detailPage, 1);
    }
}
