package tests.users;

import assertions.InventoryAssertions;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import models.LoginData;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import tests.Base_Test;
import utils.CsvDataProvider;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Swag Labs")
@Feature("Performance User")
public class PerformanceUserTest extends Base_Test {

    @Test
    void loginPerformanceUser(){

        long start = System.currentTimeMillis();

        LoginData user = CsvDataProvider.getUser("performance_glitch_user");
        InventoryPage inventoryPage = login(user);

        long elapsed = System.currentTimeMillis() - start;

        InventoryAssertions.assertLoaded(inventoryPage);
        System.out.println(elapsed + "ms");
        assertFalse(elapsed < 5000, "Login took too long: " + elapsed + "ms");
    }
}
