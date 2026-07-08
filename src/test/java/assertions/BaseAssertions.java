package assertions;

import static org.junit.jupiter.api.Assertions.*;

public abstract class BaseAssertions {

    protected static void assertPageLoaded(boolean loaded){
        assertTrue(loaded);
    }

    protected static void assertVisible(boolean visible){
        assertTrue(visible);
    }

    protected static void assertHidden(boolean hidden){
        assertFalse(hidden);
    }

    protected static void assertCount(int expected, int actual){
        assertEquals(expected, actual);
    }

    protected static void assertText(String expected, String actual){
        assertEquals(expected, actual);
    }
}
