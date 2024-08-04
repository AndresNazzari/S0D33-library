import infrastructure.Database;
import infrastructure.seeders.Seeders;
import presentation.views.login.LoginForm;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database.createDatabaseAndTables();
        // run only once to seed the database
        // Seeders.seedDatabase();

        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }
}
