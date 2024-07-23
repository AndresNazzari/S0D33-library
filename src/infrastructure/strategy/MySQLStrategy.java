package infrastructure.strategy;

import infrastructure.dbConnections.MysqlDbConnection;
import infrastructure.entities.ColumnInfo;
import infrastructure.entities.DatabaseInfo;
import infrastructure.entities.TableInfo;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class MySQLStrategy extends BaseStrategy implements DatabaseStrategy {

    @Override
    public void createTables(DatabaseInfo databaseInfo) {
        try {
            Connection connection = MysqlDbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();

            // create tables
            for (TableInfo table : databaseInfo.getTables()) {
                StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + table.getName() + " (");
                List<ColumnInfo> columns = table.getColumns();

                //create columns
                for (int i = 0; i < columns.size(); i++) {
                    ColumnInfo column = columns.get(i);
                    sql.append(column.getName()).append(" ").append(column.getType());

                    if (column.isAutoIncrement()) {
                        sql.append(" AUTO_INCREMENT");
                    }

                    sql = addOptions(sql, column);

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
            System.out.println("Error creating MySQLStrategy: " + e.getMessage());
            throw new RuntimeException("Error creating MySQLStrategy", e);
        } finally {
            System.out.println("-------------------------");
        }
    }
}
