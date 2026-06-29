package util;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

import java.io.ByteArrayInputStream;

public class ScreenshotWatcher implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        WebDriver driver = DriverFactory.getDriver();

        if(driver == null) return;

        byte[] screenshot = ScreenshotUtil.capture(driver);
        Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));
    }
}
