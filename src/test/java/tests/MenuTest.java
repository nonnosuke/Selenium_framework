package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.InventoryPage;
import pages.LoginPage;
import pages.ProductDetailPage;
import utils.DriverFactory;
import utils.ScreenshotWatcher;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Swag Labs")
@Feature("Menu")
@ExtendWith(ScreenshotWatcher.class)
public class MenuTest extends Base_Test{
    @Story("Logout")
    @DisplayName("Logout from menu bar")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void logout(){
        InventoryPage inventoryPage = loginAsStandardUser();

        inventoryPage.header().openMenu();

        LoginPage login = inventoryPage.header().menu().logout();

        assertTrue(login.loadedPage());

    }

    @Story("Navigate to All Items")
    @DisplayName("Return to Inventory page")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void allItem(){
        ProductDetailPage detailPage = loginAndOpenProductPage("Sauce Labs Backpack");
        detailPage.header().openMenu();
        InventoryPage returnedInventoryPage = detailPage.header().menu().allItems();

        assertTrue(returnedInventoryPage.loadedPage());
    }

    @Story("Reset app state")
    @DisplayName("Reset cart and app State")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void resetAppState(){
        InventoryPage inventoryPage = loginAsStandardUser();

        inventoryPage.addProductToCart("Sauce Labs Backpack");
        assertTrue(inventoryPage.header().hasCartBadge());

        inventoryPage.header().openMenu();
        inventoryPage.header().menu().resetAppState();
        assertFalse(inventoryPage.header().hasCartBadge());
    }

    @Story("Navigate to About page")
    @DisplayName("Open About page from menu")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void about(){
        InventoryPage inventoryPage = loginAsStandardUser();

        inventoryPage.header().openMenu();
        inventoryPage.header().menu().about();

        assertTrue(DriverFactory.getDriver().getCurrentUrl().startsWith("https://saucelabs.com/"));
    }
}
