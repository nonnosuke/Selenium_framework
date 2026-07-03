package tests;
import io.qameta.allure.Allure;
import models.LoginData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.LoginPage;
import utils.AllureEnvironmentWriter;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ScreenshotWatcher;

import java.util.Set;

@ExtendWith(ScreenshotWatcher.class)
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
    protected void login(String username, String password){
        loginPage().login(username, password);
    }

    //From CSV
    protected void loginAs(LoginData user) {
        Allure.step("Login as " + user.username());
        loginPage().login(
                user.username(),
                user.password()
        );
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

