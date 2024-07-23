import infrastructure.Database;

public class Main {
    public static void main(String[] args) {
        Database.createDatabaseAndTables();
        Database.seedDatabase();

    }
}
