package assertions;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import pages.InventoryPage;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class HeaderAssertions extends BaseAssertions{

    public static void assertCartIconMisaligned(InventoryPage page){
        Point actualLocation = page.header().getCartIconLocation();
        Point expectedLocation = new Point(1208, 10);

        Dimension size = page.header().getCartIconSize();

        assertNotEquals(expectedLocation, actualLocation);
        assertEquals(40, size.getWidth());
        assertEquals(40, size.getHeight());
    }

    public static void assertMenuIconRotated(InventoryPage page){
        assertTrue(page.header().hasVisualFailureClass());
    }
}
