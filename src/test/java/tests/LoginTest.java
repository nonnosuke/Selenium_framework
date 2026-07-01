package tests;

import io.qameta.allure.*;
import models.LoginData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.InventoryPage;
import utils.DriverFactory;
import utils.ScreenshotWatcher;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Swag Labs")
@Feature("Login")
@ExtendWith(ScreenshotWatcher.class)
public class LoginTest extends Base_Test {
    @DisplayName("Login test with valid and invalid users")
    @Story("Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @MethodSource("utils.CsvDataProvider#loginUsers")
    void loginTest(LoginData user) {

        loginAs(user);

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
