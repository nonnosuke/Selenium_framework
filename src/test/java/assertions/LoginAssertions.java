package assertions;

import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LoginAssertions {
    private LoginAssertions(){}

    public static void assertLoaded(LoginPage page){
        assertTrue(page.loadedPage());
    }

    public static void assertError(LoginPage page, String message){
        assertEquals(message, page.getErrorMessage());
    }
}
