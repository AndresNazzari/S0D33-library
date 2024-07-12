package infrastructure.factory;

import infrastructure.strategy.DatabaseStrategy;
import infrastructure.strategy.MySQLStrategy;
import infrastructure.strategy.SQLiteStrategy;

public class DatabaseFactory {
    public static DatabaseStrategy getDatabaseStrategy(String dbType, String url, String user, String password) {
        if ("mysql".equalsIgnoreCase(dbType)) {
            return new MySQLStrategy(url, user, password);
        } else if ("sqlite".equalsIgnoreCase(dbType)) {
            return new SQLiteStrategy(url);
        } else {
            throw new IllegalArgumentException("Unsupported database type: " + dbType);
        }
    }
}