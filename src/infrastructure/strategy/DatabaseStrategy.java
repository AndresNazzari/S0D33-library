package infrastructure.strategy;

import infrastructure.entities.DatabaseInfo;

public interface DatabaseStrategy {
    void createTables(DatabaseInfo databaseInfo);
}
