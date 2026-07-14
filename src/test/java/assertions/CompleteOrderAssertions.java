package assertions;

import pages.CompleteOrderPage;
import pages.InventoryPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompleteOrderAssertions extends BaseAssertions{
    private CompleteOrderAssertions(){}

    public static void assertLoaded(CompleteOrderPage page){
        assertPageLoaded(page.loadedPage());
    }

    public static void assertCompleteMessage(CompleteOrderPage page, String expected){
        assertEquals(expected, page.getCompleteText());
    }

    public static void assertInventoryLoaded(InventoryPage page){
        assertPageLoaded(page.loadedPage());
    }
}
