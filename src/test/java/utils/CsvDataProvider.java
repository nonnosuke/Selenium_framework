package utils;

import models.LoginData;

import java.util.stream.Stream;

public final class CsvDataProvider {
    private CsvDataProvider(){}

    public static Stream<LoginData> loginUsers(){
        return CsvReader.readLoginData("data/login.csv").stream();
    }
}
