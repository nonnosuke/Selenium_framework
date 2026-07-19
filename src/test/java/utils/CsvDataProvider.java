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

    public static Stream<ProductData> brokenAddProducts() {
        return Stream.of(
                CsvDataProvider.getProduct("Sauce Labs Bolt T-Shirt"),
                CsvDataProvider.getProduct("Sauce Labs Fleece Jacket"),
                CsvDataProvider.getProduct("Test.allTheThings() T-Shirt (Red)")
        );
    }

    public static Stream<ProductData> brokenRemoveProducts() {
        return Stream.of(
                CsvDataProvider.getProduct("Sauce Labs Backpack"),
                CsvDataProvider.getProduct("Sauce Labs Bike Light"),
                CsvDataProvider.getProduct("Sauce Labs Onesie")
        );
    }

    public static LoginData getUser(String username){
        return loginUsers()
                .filter(u -> u.username().equals(username))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found" + username));
    }

    public static ProductData getProduct(String productName){
        return PRODUCTS.stream()
                .filter(p -> p.productName().equals(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productName));
    }
}
