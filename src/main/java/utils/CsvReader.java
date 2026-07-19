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
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public final class CsvReader {
    private CsvReader(){}

    public static List<String[]> read(String resourcePath){
        List<String[]> rows = new ArrayList<>();

        InputStream input = CsvReader.class.getClassLoader().getResourceAsStream(resourcePath);

        if (input == null){
            throw new IllegalArgumentException("CSV file not found: " + resourcePath);
        }

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        input,
                                        StandardCharsets.UTF_8));

                CSVParser parser =
                        CSVFormat.DEFAULT
                                .builder()
                                .setSkipHeaderRecord(true)
                                .setIgnoreSurroundingSpaces(true)
                                .setHeader()
                                .build()
                                .parse(reader)
        ) {
            for (CSVRecord record : parser) {

                String[] row = new String[record.size()];

                for (int i = 0; i < record.size(); i++) {
                    row[i] = record.get(i);
                }

                rows.add(row);
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

            if (row.length != 4){
                throw new IllegalArgumentException("Invalid CSV row: " + Arrays.toString(row));
            }

            productData.add(new ProductData(row[0], row[1], Double.parseDouble(row[2]), row[3]));
        }
        return productData;
    }
}
