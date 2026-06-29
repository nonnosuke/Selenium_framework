package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public final class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private DriverFactory(){}

    public static WebDriver createDriver(){
        String browser = ConfigReader.get("browser");
        boolean headless = ConfigReader.getBoolean("headless");

        WebDriver webDriver;

        switch (browser.toLowerCase()){
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);

                chromeOptions.setExperimentalOption("prefs", prefs);

                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }

                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--window-size=1440,900");

                webDriver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless){
                    firefoxOptions.addArguments("--headless");
                }

                webDriver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.set(webDriver);
        return webDriver;
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void quitDriver(){
        WebDriver webDriver = driver.get();

        if(webDriver != null){
            webDriver.quit();
            driver.remove();
        }
    }
}
