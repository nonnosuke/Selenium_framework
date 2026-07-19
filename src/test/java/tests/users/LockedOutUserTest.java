package tests.users;

import assertions.LoginAssertions;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import models.LoginData;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;
import tests.Base_Test;
import utils.CsvDataProvider;

@Epic("Swag Labs")
@Feature("Locked Out User")
public class LockedOutUserTest extends Base_Test {

    @Test
    void lockedOutUserCannotLogin(){
        LoginData user = CsvDataProvider.getUser("locked_out_user");
        LoginPage loginPage = loginExpectFailure(user);

        LoginAssertions.assertError(loginPage, "Epic sadface: Sorry, this user has been locked out.");
    }
}
