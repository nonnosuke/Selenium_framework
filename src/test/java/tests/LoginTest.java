package tests;

import org.junit.jupiter.api.extension.ExtendWith;
import pages.InventoryPage;
import org.junit.jupiter.api.Test;
import util.ScreenshotWatcher;
import utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ScreenshotWatcher.class)
public class LoginTest extends Base_Test {
    @Test
    void validLoginTest(){
        InventoryPage inventoryPage = loginPage().login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        assertTrue(inventoryPage.loadedPage());
    }

    @Test
    void invalidLoginTest(){
        loginPage().invalidLogin(
                ConfigReader.get("invalid.username"),
                ConfigReader.get("invalid.password")
        );

        assertTrue(loginPage().getErrorMessage().contains("Epic sadface"));
    }
}
