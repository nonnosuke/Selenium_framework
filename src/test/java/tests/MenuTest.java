package tests;

import assertions.InventoryAssertions;
import assertions.LoginAssertions;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.InventoryPage;
import pages.LoginPage;
import pages.ProductDetailPage;
import utils.DriverFactory;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Swag Labs")
@Feature("Menu")
public class MenuTest extends Base_Test{
    @Story("Logout")
    @DisplayName("Logout from menu bar")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void logout(){
        InventoryPage inventoryPage = loginAsStandardUser();

        inventoryPage.header().openMenu();

        LoginPage login = inventoryPage.header().menu().logout();

        LoginAssertions.assertLoaded(login);

    }

    @Story("Navigate to All Items")
    @DisplayName("Return to Inventory page")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void allItem(){
        ProductDetailPage detailPage = loginAndOpenProductPage("Sauce Labs Backpack");
        detailPage.header().openMenu();
        InventoryPage returnedInventoryPage = detailPage.header().menu().allItems();

        InventoryAssertions.assertLoaded(returnedInventoryPage);
    }

    @Story("Reset app state")
    @DisplayName("Reset cart and app State")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void resetAppState(){
        InventoryPage inventoryPage = loginAsStandardUser();

        inventoryPage.addProductToCart("Sauce Labs Backpack");
        InventoryAssertions.assertCartBadgeVisible(inventoryPage);

        inventoryPage.header().openMenu();
        inventoryPage.header().menu().resetAppState();

        InventoryAssertions.assertCartBadgeHidden(inventoryPage); //Checking badge is disappeared
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
