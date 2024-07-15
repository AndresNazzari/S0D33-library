import com.fasterxml.jackson.databind.ObjectMapper;
import infrastructure.Database;
import infrastructure.entities.*;
import infrastructure.factory.DatabaseFactory;
import infrastructure.strategy.DatabaseStrategy;

import java.io.File;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        Database.createDatabaseAndTables();
        
    }
}
