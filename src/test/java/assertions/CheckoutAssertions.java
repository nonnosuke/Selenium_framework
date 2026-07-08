package assertions;

import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import pages.CompleteOrderPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class CheckoutAssertions extends BaseAssertions{
    private CheckoutAssertions(){}

    public static void assertCheckoutLoaded(CheckoutPage page){
        assertPageLoaded(page.loadedPage());
    }
    public static void assertOverviewLoaded(CheckoutOverviewPage page){
        assertPageLoaded(page.loadedPage());
    }

    public static void assertCompleteLoaded(CompleteOrderPage page){
        assertPageLoaded(page.loadedPage());
    }

    public static void assertError(CheckoutPage page, String message){
        assertText(message, page.getErrorMessage());
    }
}
