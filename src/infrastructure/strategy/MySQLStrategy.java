package infrastructure.strategy;

import infrastructure.dbConnections.MysqlDbConnection;
import infrastructure.entities.ColumnInfo;
import infrastructure.entities.DatabaseInfo;
import infrastructure.entities.ForeignKey;
import infrastructure.entities.TableInfo;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class MySQLStrategy extends BaseStrategy implements DatabaseStrategy {
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
        try {
            // Conectar a MySQL sin especificar una base de datos
            Connection connection = MysqlDbConnection.getInstance().getConnection(url, user, password);
            Statement statement = connection.createStatement();

            // Crear la base de datos si no existe
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseInfo.getName());
            System.out.println("Database " + databaseInfo.getName() + " created successfully or already exists.");

            // Cerrar la conexión inicial
            statement.close();
            connection.close();

            // Conectar a MySQL especificando la nueva base de datos
            connection = MysqlDbConnection.getInstance().getConnection(url + databaseInfo.getName(), user, password);
            statement = connection.createStatement();

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
