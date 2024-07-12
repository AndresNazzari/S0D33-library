package infrastructure.strategy;

import infrastructure.entities.ColumnInfo;
import infrastructure.entities.DatabaseInfo;
import infrastructure.entities.TableInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class MySQLStrategy implements DatabaseStrategy {
    private String url;
    private String user;
    private String password;

    public MySQLStrategy(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void createTables(DatabaseInfo databaseInfo) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseInfo.getName());
            statement.executeUpdate("USE " + databaseInfo.getName());

            for (TableInfo table : databaseInfo.getTables()) {
                StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + table.getName() + " (");
                List<ColumnInfo> columns = table.getColumns();

                for (int i = 0; i < columns.size(); i++) {
                    ColumnInfo column = columns.get(i);
                    sql.append(column.getName()).append(" ").append(column.getType());

                    if (column.isPrimaryKey()) {
                        sql.append(" PRIMARY KEY");
                    }

                    if (column.isAutoIncrement()) {
                        sql.append(" AUTO_INCREMENT");
                    }

                    if (!column.isNullable()) {
                        sql.append(" NOT NULL");
                    }

                    if (column.getDefaultValue() != null) {
                        sql.append(" DEFAULT ").append(column.getDefaultValue());
                    }

                    if (i < columns.size() - 1) {
                        sql.append(", ");
                    }
                }

                sql.append(");");
                statement.executeUpdate(sql.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
