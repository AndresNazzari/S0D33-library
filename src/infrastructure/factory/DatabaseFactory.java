package infrastructure.factory;

import infrastructure.strategy.DatabaseStrategy;
import infrastructure.strategy.MySQLStrategy;
import infrastructure.strategy.SQLiteStrategy;

public class DatabaseFactory {
    public static DatabaseStrategy getDatabaseStrategy(String dbType) {
        if ("mysql".equalsIgnoreCase(dbType)) {
            return new MySQLStrategy();
        } else if ("sqlite".equalsIgnoreCase(dbType)) {
            return new SQLiteStrategy();
        } else {
            throw new IllegalArgumentException("Unsupported database type: " + dbType);
        }
    }
}