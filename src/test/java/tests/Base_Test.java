package tests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;
import util.ScreenshotUtil;

public abstract class Base_Test {
    protected static WebDriver driver;
    protected int timeoutSeconds;
    protected boolean testFailed = false;


    @BeforeEach //This will be Executed before any Test runs in the Class
    void setUp(){
        timeoutSeconds = ConfigReader.getInt("timeout.seconds");
        driver = DriverFactory.createDriver();
        driver.get(ConfigReader.get("base.url"));
        System.out.println("SETUP CALLED");
    }

    @AfterEach //This will be Executed after any Test runs in the Class
    void tearDown(){
        if (driver != null) {
            ScreenshotUtil.attachScreenshot(driver);
            driver.quit();
        }
        System.out.println("Tear Down");
    }

    protected LoginPage loginPage(){
        return new LoginPage(driver, timeoutSeconds);
    }
}
