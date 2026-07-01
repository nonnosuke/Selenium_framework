package utils;

import models.LoginData;
import models.ProductData;

import java.util.stream.Stream;

public final class CsvDataProvider {
    private CsvDataProvider(){}

    public static Stream<LoginData> loginUsers(){
        return CsvReader.readLoginData("data/login.csv").stream();
    }

    public static Stream<ProductData> products(){
        return CsvReader.readProductData("data/products.csv").stream();
    }
}
