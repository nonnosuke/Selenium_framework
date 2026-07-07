package assertions;

import pages.ProductDetailPage;

import static org.junit.jupiter.api.Assertions.*;

public final class ProductDetailAssertions {
    private ProductDetailAssertions(){}

    public static void assertLoaded(ProductDetailPage page){
        assertTrue(page.loadedPage());
    }

    public static void assertProductName(ProductDetailPage page, String expected){
        assertEquals(expected, page.getProductName());
    }

    public static void assertDescription(ProductDetailPage page, String expected){
        assertEquals(expected, page.getDescription());
    }

    public static void assertPrice(ProductDetailPage page, double expected){
        assertEquals(expected, page.getPrice());
    }

    public static void assertAddBtnVisible(ProductDetailPage page){
        assertTrue(page.addBtnDisplayed());
    }

    public static void assertRemoveBtnVisible(ProductDetailPage page){
        assertTrue(page.removeBtnDisplayed());
    }

    public static void assertCartBadgeVisible(ProductDetailPage page){
        assertTrue(page.hasCartBadge());
    }

    public static void assertCartBadgeHidden(ProductDetailPage page){
        assertFalse(page.hasCartBadge());
    }

    public static void assertCartCount(ProductDetailPage page, int expected){
        assertEquals(expected, page.getCartCount());
    }
}
