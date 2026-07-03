package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.InventoryPage;
import pages.LoginPage;
import pages.ProductDetailPage;
import utils.ConfigReader;
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
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get(("valid.password"))
        );

        InventoryPage inventory = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);
        inventory.header().openMenu();

        LoginPage login = inventory.header().menu().logout();

        assertTrue(login.loadedPage());

    }

    @Story("Navigate to All Items")
    @DisplayName("Return to Inventory page")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void allItem(){
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get(("valid.password"))
        );

        InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);
        ProductDetailPage detailPage = inventoryPage.openProduct("Sauce Labs Backpack");
        detailPage.header().openMenu();
        InventoryPage returnedInventoryPage = detailPage.header().menu().allItems();

        assertTrue(returnedInventoryPage.loadedPage());
    }

    @Story("Reset app state")
    @DisplayName("Reset cart and app State")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void resetAppState(){
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        InventoryPage inventory = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);
        inventory.addProductToCart("Sauce Labs Backpack");
        assertTrue(inventory.header().hasCartBadge());

        inventory.header().openMenu();
        inventory.header().menu().resetAppState();
        assertFalse(inventory.header().hasCartBadge());
    }

    @Story("Navigate to About page")
    @DisplayName("Open About page from menu")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void about(){
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        InventoryPage inventory = new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);
        inventory.header().openMenu();
        inventory.header().menu().about();

        assertTrue(DriverFactory.getDriver().getCurrentUrl().startsWith("https://saucelabs.com/"));
    }
}
