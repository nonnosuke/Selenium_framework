package assertions;

import pages.ProductDetailPage;

import static org.junit.jupiter.api.Assertions.*;

public final class ProductDetailAssertions extends BaseAssertions{
    private ProductDetailAssertions(){}

    public static void assertLoaded(ProductDetailPage page){
        assertPageLoaded(page.loadedPage());
    }

    public static void assertProductName(ProductDetailPage page, String expected){
        assertText(expected, page.getProductName());
    }

    public static void assertDescription(ProductDetailPage page, String expected){
        assertText(expected, page.getDescription());
    }

    public static void assertPrice(ProductDetailPage page, double expected){
        assertEquals(expected, page.getPrice());
    }

    public static void assertAddBtnVisible(ProductDetailPage page){
        assertVisible(page.addBtnDisplayed());
    }

    public static void assertRemoveBtnVisible(ProductDetailPage page){
        assertVisible(page.removeBtnDisplayed());
    }

    public static void assertCartBadgeVisible(ProductDetailPage page){
        assertVisible(page.hasCartBadge());
    }

    public static void assertCartBadgeHidden(ProductDetailPage page){
        assertHidden(page.hasCartBadge());
    }

    public static void assertCartCount(ProductDetailPage page, int expected){
        assertCount(expected, page.getCartCount());
    }
}
