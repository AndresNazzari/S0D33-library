package infrastructure.dbConnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDbConnection {
    private static MysqlDbConnection instance = null;

    private MysqlDbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver for MySQL not found: " + e.getMessage());
            throw new RuntimeException("Driver for MySQL not found", e);
        }
    }

    public static synchronized MysqlDbConnection getInstance() {
        if (instance == null) {
            instance = new MysqlDbConnection();
            System.out.println("Instance of MySQL connection created");
            System.out.println("-------------------------");
        }
        return instance;
    }

    public Connection getConnection(String url, String username, String password) {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Error connecting to the MySQL database: " + e.getMessage());
            throw new RuntimeException("Error connecting to the MySQL database", e);
        }
    }
}
