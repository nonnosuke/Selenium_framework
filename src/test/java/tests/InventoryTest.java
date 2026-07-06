package tests;

import assertions.CartAssertions;
import assertions.InventoryAssertions;
import io.qameta.allure.*;
import models.ProductData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CartPage;
import pages.InventoryPage;
import pages.ProductDetailPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Swag Labs")
@Feature("Inventory")
public class InventoryTest extends Base_Test {
    @Story("Add product to cart")
    @DisplayName("Add item to cart")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#products")
    void addItemtoCart(ProductData product) {
        InventoryPage inventoryPage = loginAsStandardUser();

        inventoryPage.addProductToCart(product.productName());

        InventoryAssertions.assertCartCount(inventoryPage, 1);

        CartPage cartPage = inventoryPage.openCart();
        CartAssertions.assertHasProduct(cartPage, product.productName());
    }

    @Story("Remove product from Inventory Page")
    @DisplayName("Remove item from Inventory")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void removeItemFromInventory() {
        InventoryPage inventoryPage = loginAsStandardUser();

        inventoryPage.addProductToCart("Sauce Labs Backpack");

        inventoryPage.removeProductFromCart("Sauce Labs Backpack");

        InventoryAssertions.assertCartBadgeHidden(inventoryPage);

        CartPage cartPage = inventoryPage.openCart();
        CartAssertions.assertItemCount(cartPage,0);
    }

    @Story("Open each product page and move to detail page")
    @DisplayName("Open product page")
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#products")
    void openProduct(ProductData product) {
        ProductDetailPage productPage = loginAndOpenProductPage(product.productName());

        assertTrue(productPage.loadedPage());
    }

    @Story("Sort products")
    @DisplayName("Sort by Name A to Z")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void sortNameAZ(){
        InventoryPage inventoryPage = loginAsStandardUser();
        inventoryPage.sortByNameAZ();

        InventoryAssertions.assertSortedByAZ(inventoryPage);
    }

    @Story("Sort products")
    @DisplayName("Sort by Name Z to A")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void sortNameZA(){
        InventoryPage inventoryPage = loginAsStandardUser();
        inventoryPage.sortByNameZA();

        InventoryAssertions.assertSortedByZA(inventoryPage);
    }

    @Story("Sort products")
    @DisplayName("Sort by Price Low to High")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void sortPriceLowToHigh(){
        InventoryPage inventoryPage = loginAsStandardUser();
        inventoryPage.sortByPriceLowToHigh();

        InventoryAssertions.assertSortedByPriceAscending(inventoryPage);
    }

    @Story("Sort products")
    @DisplayName("Sort by Price High to Low")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void sortProceHighToLow(){
        InventoryPage inventoryPage = loginAsStandardUser();
        inventoryPage.sortByPriceHighToLow();

        InventoryAssertions.assertSortedByPriceDescending(inventoryPage);
    }

    @Story("Cart badge")
    @DisplayName("Cart badge count")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void cartBadgeCount(){
        InventoryPage inventoryPage = loginAsStandardUser();

        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.addProductToCart("Sauce Labs Bike Light");
        inventoryPage.addProductToCart("Sauce Labs Bolt T-Shirt");

        InventoryAssertions.assertCartBadgeVisible(inventoryPage);
        InventoryAssertions.assertCartCount(inventoryPage,3);

    }
}