package tests;
import io.qameta.allure.Allure;
import models.LoginData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.*;
import utils.AllureEnvironmentWriter;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ScreenshotExtension;

import java.io.File;
import java.util.Set;

@ExtendWith(ScreenshotExtension.class)
public abstract class Base_Test {
    //protected static WebDriver driver;
    protected int timeoutSeconds;


    @BeforeEach //This will be Executed before any Test runs in the Class
    void setUp(){
        timeoutSeconds = ConfigReader.getInt("timeout.seconds");
        DriverFactory.createDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));

        AllureEnvironmentWriter.write();

        System.out.println("SETUP CALLED");
    }

    @AfterEach //This will be Executed after any Test runs in the Class
    void tearDown(){
        DriverFactory.quitDriver();
        System.out.println("TEAR DOWN");
    }

    protected LoginPage loginPage(){
        return new LoginPage(DriverFactory.getDriver(), timeoutSeconds);
    }

    //From ConfigReader
    protected InventoryPage login(String username, String password){
//        loginPage().login(username, password);
        return loginPage().login(username, password);
    }

    protected LoginPage loginExpectFailure(String username, String password){
//        loginPage().login(username, password);
        return loginPage().loginExpectFailure(username, password);
    }

    //From CSV
    protected InventoryPage login(LoginData user) {
        Allure.step("Login as " + user.username());
//        loginPage().login(
//                user.username(),
//                user.password()
//        );
        return loginPage().login(user);
    }

    protected LoginPage loginExpectFailure(LoginData user) {
        Allure.step("Login as " + user.username());
//        loginPage().login(
//                user.username(),
//                user.password()
//        );
        return loginPage().loginExpectFailure(user);
    }


    protected InventoryPage loginAsStandardUser(){
        login(
                ConfigReader.get("valid.username"),
                ConfigReader.get("valid.password")
        );

        return new InventoryPage(DriverFactory.getDriver(), timeoutSeconds);
    }

    protected ProductDetailPage loginAndOpenProductPage(String productName){
        InventoryPage inventoryPage = loginAsStandardUser();
        return inventoryPage.openProduct(productName);
    }

    protected CartPage loginAndOpenCartPage(String... products){
        InventoryPage inventoryPage = loginAsStandardUser();
        for (String product : products){
            inventoryPage.addProductToCart(product);
        }
        return inventoryPage.openCart();
    }

    protected CheckoutPage loginAndOpenCheckoutPage(String... products){
        CartPage cartPage = loginAndOpenCartPage(products);
        return cartPage.checkout();
    }

    protected String getCurrentWindow(){
        return DriverFactory.getDriver().getWindowHandle();
    }

    protected void switchToNewTab(String originalWindow){
        Set<String> windows = DriverFactory.getDriver().getWindowHandles();
        for (String window : windows){
            if(!window.equals(originalWindow)){
                DriverFactory.getDriver().switchTo().window(window);
                break;
            }
        }
    }

    protected void closeCurrentTabAndReturn(String originalWindow){
        DriverFactory.getDriver().close();
        DriverFactory.getDriver().switchTo().window(originalWindow);
    }
}

