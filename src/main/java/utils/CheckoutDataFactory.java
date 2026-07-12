package utils;

import models.CheckoutData;

public final class CheckoutDataFactory {
    private CheckoutDataFactory(){}

    public static CheckoutData valid(){
        return new CheckoutData(
                "Shohei",
                "Otani",
                "V6B 1V5"
        );
    }

    public static CheckoutData withoutFirstName(){
        return new CheckoutData(
                "",
                "Otani",
                "V6B 1V5"
        );
    }

    public static CheckoutData withoutLastName(){
        return new CheckoutData(
                "Shohei",
                "",
                "V6B 1V5"
        );
    }

    public static CheckoutData withoutPostalCode(){
        return new CheckoutData(
                "Shohei",
                "Otani",
                ""
        );
    }
}
