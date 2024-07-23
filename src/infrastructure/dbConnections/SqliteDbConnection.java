package infrastructure.dbConnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteDbConnection {
    private static SqliteDbConnection instance = null;
    private Connection connection;
    private static final String URL = "jdbc:sqlite:src/resources/s0d33library.db";

    private SqliteDbConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error initializing SQLite connection: " + e.getMessage());
            throw new RuntimeException("Error initializing SQLite connection", e);
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

    public Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                this.connection = DriverManager.getConnection(URL);
            }
        } catch (SQLException e) {
            System.out.println("Error maintaining the SQLite connection: " + e.getMessage());
            throw new RuntimeException("Error maintaining the SQLite connection", e);
        }
        return this.connection;
    }
}
