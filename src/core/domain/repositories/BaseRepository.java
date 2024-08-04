package core.domain.repositories;

import infrastructure.dbConnections.SqliteDbConnection;

import java.sql.*;

public abstract class BaseRepository {

    protected abstract String getTable();

    protected Connection getConnection() {
        return SqliteDbConnection.getInstance().getConnection();
    }

    public void delete(int id) {
        String sql = "update " + getTable() + " SET deletedAt = CURRENT_TIMESTAMP WHERE id = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete failed for " + getTable() + " on id: " + id + ", no rows affected.");
            }
        } catch (SQLException e) {
            System.out.println("Error on Delete: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
