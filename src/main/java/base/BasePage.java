package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected int timeoutSeconds;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver, int timeoutSeconds){
        this.driver = driver;
        this.timeoutSeconds = timeoutSeconds;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    protected WebElement visible(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement clickable(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator){
        clickable(locator).click();
    }

    protected void type(By locator, String text){
        WebElement element = visible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String textOf(By locator){
        return visible(locator).getText();
    }
}
