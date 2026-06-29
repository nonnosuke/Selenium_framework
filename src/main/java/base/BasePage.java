package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final int timeoutSeconds;

    public BasePage(WebDriver driver, int timeoutSeconds){
        this.driver = driver;
        this.timeoutSeconds = timeoutSeconds;
    }

    protected List<WebElement> finds(By locator){
        return driver.findElements(locator);
    }

    protected boolean isDisplayed(By locator) {
        List<WebElement> elements = finds(locator);
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    protected WebElement visible(By locator){
        return WaitUtils.waitVisible(driver, locator, timeoutSeconds);
    }

    protected WebElement clickable(By locator){
        return WaitUtils.waitClickable(driver, locator, timeoutSeconds);
    }

    protected void click(By locator){
        clickable(locator).click();
    }

    protected void type(By locator, String text){
        WebElement element = visible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator){
        return visible(locator).getText();
    }
}
