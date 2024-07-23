package infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.Main;
import infrastructure.entities.DatabaseInfo;
import infrastructure.factory.DatabaseFactory;
import infrastructure.seeders.Seeders;
import infrastructure.strategy.DatabaseStrategy;

import java.io.InputStream;

public class Database {

    public static void createDatabaseAndTables() {
        try {
            ClassLoader classLoader = Main.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("resources/database.json");

            if (inputStream == null) {
                throw new IllegalArgumentException("file not found! " + "src/resources/database.json");
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                DatabaseInfo databaseInfo = objectMapper.readValue(inputStream, DatabaseInfo.class);

                // create MySQL tables
                DatabaseStrategy strategy = DatabaseFactory.getDatabaseStrategy("mysql");
                strategy.createTables(databaseInfo);

                // Para SQLite
                DatabaseStrategy sqliteStrategy = DatabaseFactory.getDatabaseStrategy("sqlite");
                sqliteStrategy.createTables(databaseInfo);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException("Error", e);
        }
    }

    public static void seedDatabase() {
        // code to seed the database
        Seeders.seedAuthors();
        Seeders.seedBooks();
    }
}
