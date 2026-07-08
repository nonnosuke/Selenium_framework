package assertions;

import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LoginAssertions extends BaseAssertions{
    private LoginAssertions(){}

    public static void assertLoaded(LoginPage page){
        assertPageLoaded(page.loadedPage());
    }

    public static void assertError(LoginPage page, String message){
        assertText(message, page.getErrorMessage());
    }
}
