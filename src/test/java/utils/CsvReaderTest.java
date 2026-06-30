package utils;

import models.LoginData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvReaderTest {
    @Test
    void shouldReadCsv(){
        List<LoginData> users = CsvReader.readLoginData("data/login.csv");

        assertEquals(3, users.size());
        LoginData first = users.get(0);

        assertEquals("standard_user", first.username());
        assertEquals("secret_sauce", first.password());
        assertEquals("SUCCESS", first.expected());

    }
}
