package assertions;

import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import pages.CompleteOrderPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class CheckoutAssertions {
    private CheckoutAssertions(){}

    public static void assertCheckoutLoaded(CheckoutPage page){
        assertTrue(page.loadedPage());
    }
    public static void assertOverviewLoaded(CheckoutOverviewPage page){
        assertTrue(page.loadedPage());
    }

    public static void assertCompleteLoaded(CompleteOrderPage page){
        assertTrue(page.loadedPage());
    }

    public static void assertError(CheckoutPage page, String message){
        assertEquals(message, page.getErrorMessage());
    }
}
