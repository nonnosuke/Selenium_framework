package assertions;

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
        List<String> actual = page.getProductNames();

        List<String> expected = new ArrayList<>(actual);
        Collections.sort(expected);

        assertEquals(expected, actual);
    }

    public static void assertSortedByZA(InventoryPage page){
        List<String> actual = page.getProductNames();

        List<String> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());

        assertEquals(expected, actual);
    }

    public static void assertSortedByPriceAscending(InventoryPage page){
        List<Double> actual = page.getProductPrices();

        List<Double> expected = new ArrayList<>(actual);
        Collections.sort(expected);

        assertEquals(expected, actual);
    }

    public static void assertSortedByPriceDescending(InventoryPage page){
        List<Double> actual = page.getProductPrices();

        List<Double> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());

        assertEquals(expected, actual);
    }

}
