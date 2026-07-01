package utils;

import models.LoginData;
import models.ProductData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CsvReader {
    private CsvReader(){}

    public static List<String[]> read(String resourcePath){
        List<String[]> rows = new ArrayList<>();

        InputStream input = CsvReader.class.getClassLoader().getResourceAsStream(resourcePath);

        if (input == null){
            throw new IllegalArgumentException("CSV file not found: " + resourcePath);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))){
            String line;

            reader.readLine(); //変数に保存していないため、Headerは受け取らない

            while ((line = reader.readLine()) != null){
                if (line.isBlank()){
                    continue;
                }
                rows.add(line.split(","));
            }

        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return rows;
    }

    public static List<LoginData> readLoginData(String resourcePath){
        List<String[]> csvRows = read(resourcePath);
        List<LoginData> loginData = new ArrayList<>();
        for(String[] row : csvRows){

            if (row.length != 3){
                throw new IllegalArgumentException("Invalid CSV row: " + Arrays.toString(row));
            }

            loginData.add(new LoginData(row[0], row[1], row[2]));
        }
        return loginData;
    }

    public static List<ProductData> readProductData(String resourcePath){
        List<String[]> csvRows = read(resourcePath);
        List<ProductData> productData = new ArrayList<>();
        for(String[] row : csvRows){

            if (row.length != 1){
                throw new IllegalArgumentException("Invalid CSV row: " + Arrays.toString(row));
            }

            productData.add(new ProductData(row[0]));
        }
        return productData;
    }
}
