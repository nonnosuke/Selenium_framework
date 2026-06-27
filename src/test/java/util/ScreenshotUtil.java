package util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;


public class ScreenshotUtil{
    public static void attachScreenshot(WebDriver driver){
        byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
    }
}


/*Save to screenshots folder

    private static final String SCREENSHOT_FOLDER = "screenshots";

    public static final String takeScreenshot(WebDriver driver, String testName){
        if(driver == null){
            return null;
        }

        try {
            Path folder = Paths.get(SCREENSHOT_FOLDER);
            Files.createDirectories(folder);

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            String fileName = testName + "_" + timestamp + ".png";

            Path destination = folder.resolve(fileName);

            File screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Files.copy(screenshot.toPath(), destination);
            System.out.println("Screenshot saved: " + destination.toAbsolutePath());
            return destination.toString();
        } catch (IOException e){
            System.out.println("Failed to save screenshot");
            e.printStackTrace();
            return null;
        }
}*/
