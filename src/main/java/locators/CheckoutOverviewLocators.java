package locators;

import org.openqa.selenium.By;

public final class CheckoutOverviewLocators {
    private CheckoutOverviewLocators(){}

    public static final By PAGE_TITLE = By.className("title");
    public static final By CART_ITEM = By.className("cart_item");
    public static final By ITEM_TOTAL = By.className("summary_subtotal_label");
    public static final By TAX = By.className("summary_tax_label");
    public static final By TOTAL = By.className("summary_total_label");
    public static final By FINISH_BTN = By.id("finish");
    public static final By CANCEL_BTN = By.id("cancel");

}
