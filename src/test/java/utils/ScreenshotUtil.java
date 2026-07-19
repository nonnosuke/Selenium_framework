package utils;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;


public class ScreenshotUtil{
    public static byte[] capture(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static BufferedImage captureImage(WebDriver driver){
        try{
            byte[] bytes = capture(driver);
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException e){
            throw new RuntimeException(e);
        }
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



