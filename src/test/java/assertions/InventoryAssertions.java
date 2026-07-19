package assertions;

import models.ProductData;
import org.openqa.selenium.Rectangle;
import pages.InventoryPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public final class InventoryAssertions extends BaseAssertions {
    InventoryAssertions(){}

    public static void assertLoaded(InventoryPage page){
        assertPageLoaded(page.loadedPage());
    }

    public static void assertCartBadgeVisible(InventoryPage page){
        assertVisible(page.header().hasCartBadge());
    }

    public static void assertCartBadgeHidden(InventoryPage page){
        assertHidden(page.header().hasCartBadge());
    }

    public static void assertCartCount(InventoryPage page, int expected){
        assertCount(expected, page.header().getCartBadgeCount());
    }

    public static void assertSortedByAZ(InventoryPage page){
        List<String> actual = page.items().getProductNames();

        List<String> expected = new ArrayList<>(actual);
        Collections.sort(expected);

        assertEquals(expected, actual);
    }

    public static void assertSortedByZA(InventoryPage page){
        List<String> actual = page.items().getProductNames();

        List<String> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());

        assertEquals(expected, actual);
    }

    public static void assertSortedByPriceAscending(InventoryPage page){
        List<Double> actual = page.items().getProductPrices();

        List<Double> expected = new ArrayList<>(actual);
        Collections.sort(expected);

        assertEquals(expected, actual);
    }

    public static void assertSortedByPriceDescending(InventoryPage page){
        List<Double> actual = page.items().getProductPrices();

        List<Double> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());

        assertEquals(expected, actual);
    }

    public static void assertIncorrectProductImage(InventoryPage page, ProductData product){
        String actual = page.items().getProductImage(product.productName());

        System.out.println("Actual : " + actual);
        System.out.println("Expected : " + product.image());

        //assertTrue(actual.contains(product.image()));
        assertFalse(actual.contains(product.image()));
    }

    public static void assertAddBtnMisaligned(InventoryPage page, ProductData product){
        Rectangle card =
                page.items().getProductCardRect(product.productName());

        Rectangle button =
                page.items().getAddBtnRect(product.productName());

        int offset =
                button.getX() - card.getX();

        assertNotEquals(310, offset);
    }

    public static void assertProductNameRightAligned(InventoryPage page, ProductData product){
        assertTrue(page.items().isProductNameRightAligned(product.productName()), product.productName());
    }

    public static void assertIncorrectPrice(InventoryPage page, ProductData product){
        assertNotEquals(product.price(), page.getInventoryProductPrice(product.productName()));
    }
}
