package com.goit.javaonline5.note.connection;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class DbInitService {

    private DbInitService() {
        throw new IllegalStateException("Utility class");
    }

    @SneakyThrows
    public static void initDb() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream reader = loader.getResourceAsStream("application.properties");

        Properties properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        String dbUrl = properties.getProperty("spring.datasource.url");
        String dbUser = properties.getProperty("spring.datasource.username");
        String dbPass = properties.getProperty("spring.datasource.password");

        Flyway flyway = Flyway
                .configure()
                .dataSource(dbUrl, dbUser, dbPass)
                .load();
        flyway.migrate();
    }
}
