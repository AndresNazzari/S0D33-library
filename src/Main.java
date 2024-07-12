import com.fasterxml.jackson.databind.ObjectMapper;
import infrastructure.entities.*;
import infrastructure.factory.DatabaseFactory;
import infrastructure.strategy.DatabaseStrategy;

import java.io.File;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        System.out.print("Hello and welcome!");

        try {
            ClassLoader classLoader = Main.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("resources/database.json");

            if (inputStream == null) {
                throw new IllegalArgumentException("file not found! " + "src/resources/database.json");
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                DatabaseInfo databaseInfo = objectMapper.readValue(inputStream, DatabaseInfo.class);

                // Crear tablas en MySQL
                DatabaseStrategy strategy = DatabaseFactory.getDatabaseStrategy("mysql", "jdbc:mysql://localhost:3306/", "root", "");
                strategy.createTables(databaseInfo);

                // Para SQLite
                DatabaseStrategy sqliteStrategy = DatabaseFactory.getDatabaseStrategy("sqlite", "jdbc:sqlite:src/resources/" + databaseInfo.getName() + ".db", null, null);
                sqliteStrategy.createTables(databaseInfo);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            //e.printStackTrace();
        }
    }
}
