package tests;

import assertions.FooterAssertions;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;


@Epic("Swag Labs")
@Feature("Footer")
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

        FooterAssertions.assertOpenedUrlContains("x");

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

        FooterAssertions.assertOpenedUrlContains("facebook");
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

        FooterAssertions.assertOpenedUrlContains("linkedin");
        closeCurrentTabAndReturn(originalWindow);
    }
}