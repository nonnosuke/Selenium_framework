package assertions;

import pages.CheckoutOverviewPage;
import pages.CompleteOrderPage;
import pages.InventoryPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class CheckoutOverviewAssertions extends BaseAssertions{
    private CheckoutOverviewAssertions(){}

    public static void assertLoaded(CheckoutOverviewPage page){
        assertPageLoaded(page.loadedPage());
    }

    public static void assertItemTotal(CheckoutOverviewPage page, double expected){
        assertEquals(expected, page.getItemTotal());
    }

    public static void assertTax(CheckoutOverviewPage page, double expected){
        assertEquals(expected, page.getTax());
    }

    public static void assertTotal(CheckoutOverviewPage page, double expected){
        assertEquals(expected, page.getTotal());
    }

    public static void assertCartItems(CheckoutOverviewPage page, int expected){
        assertEquals(expected, page.getCartItems().size());
    }

    public static void assertInventoryLoaded(InventoryPage page){
        assertPageLoaded(page.loadedPage());
    }

    public static void assertCompleteLoaded(CompleteOrderPage page){
        assertPageLoaded(page.loadedPage());
    }
}
