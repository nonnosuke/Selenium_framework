package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.InventoryPage;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ScreenshotWatcher;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Swag Labs")
@Feature("Footer")
@ExtendWith(ScreenshotWatcher.class)
public class FooterTest extends Base_Test {
    @Story("Open X")
    @DisplayName("Open X from footer")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void openX() {
        InventoryPage inventoryPage = loginAsStandardUser();

        String originalWindow = getCurrentWindow();
        inventoryPage.footer().openX();

        switchToNewTab(originalWindow);

        assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("x"));

        closeCurrentTabAndReturn(originalWindow);
    }

    @Story("Open Facebook")
    @DisplayName("Open Facebook from footer")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void openFacebook() {
        InventoryPage inventoryPage = loginAsStandardUser();

        String originalWindow = getCurrentWindow();
        inventoryPage.footer().openFacebook();

        switchToNewTab(originalWindow);

        assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("facebook"));
        closeCurrentTabAndReturn(originalWindow);
    }

    @Story("Open LinkedIn")
    @DisplayName("Open LinkedIn from footer")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void openLinkedIn() {
        InventoryPage inventoryPage = loginAsStandardUser();

        String originalWindow = getCurrentWindow();
        inventoryPage.footer().openLinkedIn();

        switchToNewTab(originalWindow);

        assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("linkedin"));
        closeCurrentTabAndReturn(originalWindow);
    }
}