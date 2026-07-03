package pages.components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FooterComponent extends BasePage {
    public FooterComponent(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    private final By xBtn = By.cssSelector("[data-test='social-twitter']");
    private final By facebookBtn = By.cssSelector("[data-test='social-facebook']");
    private final By linkedInBtn = By.cssSelector("[data-test='social-linkedin']");

    public void openX(){
        click(xBtn);
    }

    public void openFacebook(){
        click(facebookBtn);
    }

    public void openLinkedIn(){
        click(linkedInBtn);
    }
}
