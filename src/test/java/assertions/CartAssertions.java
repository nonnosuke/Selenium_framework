package assertions;

import pages.CartPage;
import static org.junit.jupiter.api.Assertions.*;


public final class CartAssertions extends BaseAssertions {
    private CartAssertions(){}

    public static void assertLoaded(CartPage page){
        assertPageLoaded(page.loadedPage());
    }

    public static void assertCartBadgeCount(CartPage page, int expected){
        assertCount(expected, page.getCartBadgeCount());
    }

    public static void assertHasProduct(CartPage page, String productName){
        assertTrue(page.cartItems().hasProduct(productName));
    }

    public static void assertNotHaveProduct(CartPage page, String productName){
        assertFalse(page.cartItems().hasProduct(productName));
    }

    public static void assertItemCount(CartPage page, int expected){
        assertCount(expected, page.cartItems().getItemCount());
    }
}
