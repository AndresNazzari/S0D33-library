package infrastructure.seeders;

import infrastructure.dbConnections.SqliteDbConnection;

import java.sql.Connection;

public class Seeders {

    public Seeders() {
        // code to seed the database
    }

    public static void seedUsers() {
        Connection sqliteConnection = SqliteDbConnection.getInstance().getConnection();

    }

    public static void seedStaff() {
    }

    public static void seedStudents() {
    }

    // code to seed the database
    public static void seedAuthors() {
        // code to seed the database
    }

    public static void seedBooks() {
        // code to seed the database
    }
}
