package tests;

import models.LoginData;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.InventoryPage;
import org.junit.jupiter.api.Test;
import utils.DriverFactory;
import utils.ScreenshotWatcher;
import utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(ScreenshotWatcher.class)
public class LoginTest extends Base_Test {
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#loginUsers")
    void loginTest(LoginData user) {
        loginPage().login(
                user.username(),
                user.password()
        );

        if (user.expected().equals("SUCCESS")) {
            assertTrue(
                    new InventoryPage(
                            DriverFactory.getDriver(),
                            timeoutSeconds).loadedPage());
        } else {
            assertTrue(loginPage().loadedPage());
            assertEquals("Epic sadface: Sorry, this user has been locked out.",
                    loginPage().getErrorMessage());
        }
    }
}
