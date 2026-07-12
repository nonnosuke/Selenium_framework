package utils;

import models.LoginData;
import models.ProductData;

import java.util.List;
import java.util.stream.Stream;

public final class CsvDataProvider {
    private CsvDataProvider(){}

    private static final List<ProductData> PRODUCTS =
            CsvReader.readProductData("data/products.csv");


    public static Stream<LoginData> loginUsers(){
        return CsvReader.readLoginData("data/login.csv").stream();
    }

    public static Stream<ProductData> products(){
        return PRODUCTS.stream();
                //CsvReader.readProductData("data/products.csv").stream();
    }

    public static ProductData getProduct(String productName){
        return PRODUCTS.stream()
                .filter(p -> p.productName().equals(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productName));
    }
}
