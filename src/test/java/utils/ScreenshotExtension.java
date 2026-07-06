package utils;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class ScreenshotExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        WebDriver driver = DriverFactory.getDriver();

        if(driver != null) {
            byte[] screenshot = ScreenshotUtil.capture(driver);

            Allure.addAttachment("Failure Screenshot",
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    ".png");
        }
        throw throwable;
    }
}
