package infrastructure.repositories;

import core.application.mappings.Mapper;
import core.domain.entities.User;
import core.domain.repositories.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends BaseRepository {
    private final String table = "users";

    public List<User> getAllStudents() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM " + getTable() + " WHERE isAdmin = 0 AND deletedAt is null";
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            User user = new User(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("userName"),
                    rs.getString("password"),
                    rs.getInt("isAdmin")
            );
            users.add(user);
        }
        return users;
    }

    public User getById(int id) throws SQLException {
        String sql = "SELECT * FROM " + getTable() + " WHERE id = ?";
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return Mapper.DbUserToUser(rs);
        }

        return null;
    }

    public String getPopularUser() throws SQLException {
        String sql = "SELECT u.firstName, u.lastName, COUNT(l.userId) as lendCount FROM lends l " +
                "INNER JOIN users u ON u.id = l.userId " +
                "GROUP BY l.userId " +
                "ORDER BY lendCount DESC " +
                "LIMIT 1";

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getString("firstName") + " " + rs.getString("lastName");
        }
        return null;
    }

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM " + getTable() + " WHERE userName = ?";

        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return Mapper.DbUserToUser(rs);
        }

        return new User();
    }

    public User create(User user) throws SQLException {
        String sql = "INSERT INTO " + getTable() + " (firstName, lastName, userName, password, isAdmin) VALUES (?, ?, ?, ?, ?)";

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getFirstName());
        stmt.setString(2, user.getLastName());
        stmt.setString(3, user.getUserName());
        stmt.setString(4, user.getPassword());
        stmt.setInt(5, user.isAdmin());

        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }

        } else {
            throw new SQLException("Creating book failed, no rows affected.");
        }
        return user;
    }

    public void update(User user) throws SQLException {
        String sql = "UPDATE " + getTable() + " SET firstName = ?, lastName = ?, userName = ?, password = ?, isAdmin = ? WHERE id = ?";
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, user.getFirstName());
        stmt.setString(2, user.getLastName());
        stmt.setString(3, user.getUserName());
        stmt.setString(4, user.getPassword());
        stmt.setInt(5, user.isAdmin());
        stmt.setInt(6, user.getId());

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Update failed for " + getTable() + " on id: " + user.getId() + ", no rows affected.");
        }
    }

    @Override
    protected String getTable() {
        return table;
    }
}
