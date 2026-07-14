package pages.components;

import base.BasePage;
import locators.PriceSummaryLocators;
import org.openqa.selenium.WebDriver;

public class PriceSummaryComponent extends BasePage {
    public PriceSummaryComponent(WebDriver driver, int timeoutSeconds) {
        super(driver, timeoutSeconds);
    }

    public double getSubTotal(){
        String text = getText(PriceSummaryLocators.ITEM_TOTAL);
        return Double.parseDouble(text.replace("Item total: $", ""));
    }

    public double getTax(){
        String text = getText(PriceSummaryLocators.TAX);
        return Double.parseDouble(text.replace("Tax: $", ""));
    }

    public double getTotal(){
        String text = getText(PriceSummaryLocators.TOTAL);
        return Double.parseDouble(text.replace("Total: $", ""));
    }
}
