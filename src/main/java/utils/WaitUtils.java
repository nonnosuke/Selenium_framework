package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtils {
    private WaitUtils(){}

    public static WebElement waitVisible(WebDriver driver, By locator, int timeoutSeconds){
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitClickable(WebDriver driver, By locator, int timeoutSeconds){
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds)).until(ExpectedConditions.elementToBeClickable(locator));
    }
}
