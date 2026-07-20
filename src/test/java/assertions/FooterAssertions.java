package assertions;

import utils.DriverFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class FooterAssertions extends BaseAssertions{

    public static void assertOpenedUrlContains(String expected){
        assertTrue(DriverFactory.getDriver().getCurrentUrl().contains(expected));
    }
}
