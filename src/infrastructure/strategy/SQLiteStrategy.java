package infrastructure.strategy;

import infrastructure.entities.ColumnInfo;
import infrastructure.entities.DatabaseInfo;
import infrastructure.entities.TableInfo;
import infrastructure.sqlite.SqliteDbConnection;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class SQLiteStrategy implements DatabaseStrategy {
    private String url;

    public SQLiteStrategy(String url) {
        this.url = url;
    }

    @Override
    public void createTables(DatabaseInfo databaseInfo) {
        try (Connection connection = SqliteDbConnection.getInstance().getConnection(url)) {

            Statement statement = connection.createStatement();
            for (TableInfo table : databaseInfo.getTables()) {
                StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + table.getName() + " (");
                List<ColumnInfo> columns = table.getColumns();

                for (int i = 0; i < columns.size(); i++) {
                    ColumnInfo column = columns.get(i);
                    sql.append(column.getName()).append(" ").append(column.getType());

                    if (column.isPrimaryKey()) {
                        sql.append(" PRIMARY KEY");
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

