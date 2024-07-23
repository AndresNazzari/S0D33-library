package infrastructure.dbConnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDbConnection {
    private static MysqlDbConnection instance = null;
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/s0d33library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private MysqlDbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error initializing MySQL connection: " + e.getMessage());
            throw new RuntimeException("Error initializing MySQL connection", e);
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

    public Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            System.out.println("Error maintaining the MySQL connection: " + e.getMessage());
            throw new RuntimeException("Error maintaining the MySQL connection", e);
        }
        return this.connection;
    }
}
