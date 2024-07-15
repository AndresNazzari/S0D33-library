package infrastructure.dbConnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteDbConnection {
    private static SqliteDbConnection instance = null;

    private SqliteDbConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver for Sqlite not found: " + e.getMessage());
            throw new RuntimeException("Driver for Sqlite not found", e);
        }
    }

    public static synchronized SqliteDbConnection getInstance() {
        if (instance == null) {
            instance = new SqliteDbConnection();
            System.out.println("Instance of Sqlite connection created");
            System.out.println("-------------------------");
        }
        return instance;
    }

    public Connection getConnection(String url) {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error connecting to the Sqlite database : " + e.getMessage());
            throw new RuntimeException("Error connecting to the Sqlite database ", e);
        }
    }
}
