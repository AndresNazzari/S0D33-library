package infrastructure.strategy;

import infrastructure.entities.ColumnInfo;
import infrastructure.entities.DatabaseInfo;
import infrastructure.entities.ForeignKey;
import infrastructure.entities.TableInfo;
import infrastructure.dbConnections.SqliteDbConnection;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class SQLiteStrategy extends BaseStrategy implements DatabaseStrategy {
    private String url;

    public SQLiteStrategy(String url) {
        this.url = url;
    }

    @Override
    public void createTables(DatabaseInfo databaseInfo) {
        try {
            Connection connection = SqliteDbConnection.getInstance().getConnection(url);
            Statement statement = connection.createStatement();
            System.out.println("Database " + databaseInfo.getName() + " created successfully or already exists.");

            // create tables
            for (TableInfo table : databaseInfo.getTables()) {
                StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + table.getName() + " (");
                List<ColumnInfo> columns = table.getColumns();

                //create columns
                for (int i = 0; i < columns.size(); i++) {
                    ColumnInfo column = columns.get(i);
                    sql.append(column.getName());

                    if (column.isAutoIncrement() && column.getType().equalsIgnoreCase("INT")) {
                        sql.append(" INTEGER PRIMARY KEY AUTOINCREMENT");
                    } else {
                        sql = addOptions(sql, column);
                    }

                    if (i < columns.size() - 1) {
                        sql.append(", ");
                    }
                }

                sql = addFk(sql, table);

                sql.append(");");
                statement.executeUpdate(sql.toString());
                System.out.println("Table " + table.getName() + " created successfully or already exists.");

            }
        } catch (Exception e) {
            System.out.println("Error creating SQLiteStrategy: " + e.getMessage());
            throw new RuntimeException("Error creating SQLiteStrategy", e);
        } finally {
            System.out.println("-------------------------");
        }
    }
}

