package utils;

public final class PriceCalculator {
    private PriceCalculator(){}

    private static final double TAX_RATE = 0.08;

    public static double itemTotal(double... prices){
        double total = 0;

        for (double price : prices){
            total += price;
        }
        return round(total);
    }

    public static double tax(double itemTotal){
        return round(itemTotal * TAX_RATE);
    }

    public static double total(double itemTotal){
        return round(itemTotal + tax(itemTotal));
    }

    public static double round(double value){
        return Math.round(value * 100.0) / 100.0;
    }
}
