package goit.connection;

import lombok.SneakyThrows;
import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbInitService {

   @SneakyThrows
    public void initDb() {
       ClassLoader loader = Thread.currentThread().getContextClassLoader();
       InputStream reader = loader.getResourceAsStream("application.properties");

       Properties properties = new Properties();
       try {
           properties.load(reader);
       } catch (IOException e) {
           throw new RuntimeException(e);
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