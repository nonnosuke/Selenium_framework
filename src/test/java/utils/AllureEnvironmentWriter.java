package utils;

import io.qameta.allure.Allure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class AllureEnvironmentWriter {
    private AllureEnvironmentWriter(){}

    public static void write(){
        try {
            Path path = Path.of(
                    "target",
                    "allure-results",
                    "environment.properties"
            );

            Files.createDirectories(path.getParent());
            String content = """
                    Browser=%s
                    Base URL=%s
                    Java=%s
                    OS=%s
                    Architecture=%s
                    Language=%s
                    """.formatted(
                            ConfigReader.get("browser"),
                            ConfigReader.get("base.url"),
                            ConfigReader.get("java"),
                            ConfigReader.get("os"),
                            ConfigReader.get("architecture"),
                            ConfigReader.get("language")
            );

            Files.writeString(path, content);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
