package locators;

import org.openqa.selenium.By;

public final class InventoryLocators {
    private InventoryLocators(){}

    // -------- Static Locators ---------
    public static final By PAGE_TITLE = By.className("title");

    public static final By SORT_DROPDOWN = By.className("product_sort_container");

    public static final By INVENTORY_ITEM_NAMES = By.className("inventory_item_name");

    public static final By INVENTORY_PRICES = By.className("inventory_item_price");

    // -------- Dynamic Locators --------
    public static By addBtn(String productName){
        return By.xpath("//div[contains(@class,'inventory_item')]" +
                "[.//div[text()='" + productName + "']]//button");
    }

    public static By removeBtn(String productName){
        return By.xpath("//div[text()='" + productName +
                "']/ancestor::div[@class='inventory_item']//button");
    }

    public static By button(String productName){
        return By.xpath(
                "//div[contains(@class,'inventory_item')]" +
                        "[.//div[text()='" + productName + "']]" +
                        "//button"
        );
    }

    public static By product(String productName){
        return By.xpath( "//div[text()='" + productName + "']");
    }

    public static By productImage(String productName){
        return By.xpath( "//div[text()='" + productName + "']" +
                "/ancestor::div[@class='inventory_item']" +
                "//img");
    }

    public static By productCard(String productName){
        return By.xpath(
                "//div[contains(@class,'inventory_item')]" +
                        "[.//div[text()='" + productName + "']]"
        );
    }

    public static By productPrice(String productName){
        return By.xpath(
                "//div[text()='" + productName + "']" +
                        "/ancestor::div[contains(@class,'inventory_item')]" +
                        "//div[@class='inventory_item_price']"
        );
    }
}
