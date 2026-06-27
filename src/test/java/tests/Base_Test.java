package tests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

public abstract class Base_Test {
    protected WebDriver driver;
    protected int timeoutSeconds;

    @BeforeEach //This will be Executed before any Test runs in the Class
    void setUp(){
        timeoutSeconds = ConfigReader.getInt("timeout.seconds");
        driver = DriverFactory.createDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("base.url"));
        System.out.println("SETUP CALLED");
    }

    @AfterEach //This will be Executed after any Test runs in the Class
    void tearDown(){
        if (driver != null) {
            driver.quit();
            System.out.println("Tear Down");
        }
    }

    protected LoginPage loginPage(){
        return new LoginPage(driver, timeoutSeconds);
    }
}
