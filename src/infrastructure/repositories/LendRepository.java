package infrastructure.repositories;

import core.application.mappings.Mapper;
import core.domain.entities.Lend;
import core.domain.repositories.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LendRepository extends BaseRepository {
    private final String table = "lends";

    public List<Lend> getAll() throws SQLException {
        List<Lend> lends = new ArrayList<>();
        String sql = "SELECT l.id, l.lendDate, l.dueDate, l.userId, u.firstName, u.lastName, l.bookId, b.title FROM " + getTable() + " l " +
                "INNER JOIN users u ON u.id = l.userId " +
                "INNER JOIN books b ON b.id = l.id " +
                "WHERE l.returnDate IS NULL";

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            lends.add(Mapper.DbLendToLend(rs));
        }
        return lends;
    }

    public String lendBook(int userId, int bookId, String dueDate) throws SQLException {
        String sql = "INSERT INTO " + getTable() + " (userId, bookId, dueDate) VALUES (?, ?, ?)";
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, userId);
        stmt.setInt(2, bookId);
        stmt.setString(3, dueDate);
        int affectedRows = stmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Lend failed for " + getTable() + ", no rows affected.");
        }
        return "Lend created successfully";
    }

    public String returnLend(int lendId) throws SQLException {
        String sql = "UPDATE " + getTable() + " SET returnDate = CURRENT_TIMESTAMP WHERE id = ?";
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, lendId);
        int affectedRows = stmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Return failed for " + getTable() + " on id: " + lendId + ", no rows affected.");
        }
        return "Lend returned successfully";
    }

    public String updateDueDate(int id, String dueDate) throws SQLException {
        String sql = "UPDATE " + getTable() + " SET dueDate = ? WHERE id = ?";
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, dueDate);
        stmt.setInt(2, id);
        int affectedRows = stmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Update failed for " + getTable() + " on id: " + id + ", no rows affected.");
        }
        return "Due date updated successfully";

    }

    @Override
    protected String getTable() {
        return table;
    }
}
