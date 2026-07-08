package assertions;

import models.CartItem;
import pages.CartPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public final class CartAssertions extends BaseAssertions {
    private CartAssertions(){}

    public static void assertLoaded(CartPage page){
        assertPageLoaded(page.loadedPage());
    }

    public static void assertCartCount(CartPage page, int expected){
        assertCount(expected, page.getCartCount());
    }

    public static void assertHasProduct(CartPage page, String productName){
        assertTrue(page.hasProduct(productName));
    }

    public static void assertNotHaveProduct(CartPage page, String productName){
        assertFalse(page.hasProduct(productName));
    }

    public static void assertItemCount(CartPage page, int expected){
        List<CartItem> items = page.getCartItems();
        assertEquals(expected, items.size());
    }
}
