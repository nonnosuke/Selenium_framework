package tests.users;

import assertions.InventoryAssertions;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.LoginData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import tests.Base_Test;
import utils.CsvDataProvider;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Swag Labs")
@Feature("Performance User")
public class PerformanceUserTest extends Base_Test {

    @Story("Glitch user takes time to log in")
    @DisplayName("Log in takes more than 5 seconds")
    @Test
    void loginIsDelayed(){

        long start = System.currentTimeMillis();

        LoginData user = CsvDataProvider.getUser("performance_glitch_user");
        InventoryPage inventoryPage = login(user);

        long elapsed = System.currentTimeMillis() - start;

        InventoryAssertions.assertLoaded(inventoryPage);
        assertTrue(elapsed >= 5000, "Login took too long: " + elapsed + "ms");
    }
}
