package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ScreenshotUtil{
    public static byte[] capture(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    /*Save to screenshots folder
    public static void saveToFile(WebDriver driver, Path path) {
        File src = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        try {
            Files.copy(src.toPath(), path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
}



