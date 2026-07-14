package locators;

import org.openqa.selenium.By;

public final class PriceSummaryLocators {
    private PriceSummaryLocators(){}

    public static final By ITEM_TOTAL = By.className("summary_subtotal_label");
    public static final By TAX = By.className("summary_tax_label");
    public static final By TOTAL = By.className("summary_total_label");
}
