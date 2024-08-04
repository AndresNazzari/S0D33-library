package infrastructure.repositories;

import core.application.mappings.Mapper;
import core.domain.entities.Book;
import core.domain.repositories.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository extends BaseRepository {
    private final String table = "books";

    public List<Book> getAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM " + getTable() + " WHERE deletedAt is null AND available = 1";

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setPublishYear(rs.getInt("publishYear"));
            book.setDescription(rs.getString("description"));
            book.setAuthor(rs.getString("author"));
            book.setGenre(rs.getString("genre"));
            books.add(book);
        }
        return books;
    }

    public String getPopularBook() throws SQLException {
        String sql = "SELECT b.title, COUNT(l.bookId) as lendCount FROM lends l " +
                "INNER JOIN books b ON b.id = l.bookId " +
                "GROUP BY l.bookId " +
                "ORDER BY lendCount DESC " +
                "LIMIT 1";

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getString("title");
        }
        return null;
    }

    public Book getById(int id) throws SQLException {
        String sql = "SELECT * FROM " + getTable() + " WHERE id = ? AND deletedAt IS NULL";

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return Mapper.DbBookToBook(rs);
        }

        return null;
    }

    public Book create(Book book) throws SQLException {
        String sql = "INSERT INTO " + getTable() + " (title, publishYear, description, author, genre) VALUES (?, ?, ?, ?, ?)";
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, book.getTitle());
        stmt.setInt(2, book.getPublishYear());
        stmt.setString(3, book.getDescription());
        stmt.setString(4, book.getAuthor());
        stmt.setString(5, book.getGenre());

        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                book.setId(rs.getInt(1));
            }
        } else {
            throw new SQLException("Creating book failed, no rows affected.");
        }
        return book;
    }

    public void update(Book book) throws SQLException {
        String sql = "UPDATE " + getTable() + " SET title = ?, publishYear = ?, description = ?, author = ?, genre = ? WHERE id = ?";
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, book.getTitle());
        stmt.setInt(2, book.getPublishYear());
        stmt.setString(3, book.getDescription());
        stmt.setString(4, book.getAuthor());
        stmt.setString(5, book.getGenre());
        stmt.setInt(6, book.getId());

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Update failed for " + getTable() + " on id: " + book.getId() + ", no rows affected.");
        }
    }

    public void updateAvailability(int bookId, int available) throws SQLException {
        String sql = "UPDATE " + getTable() + " SET available = ? WHERE id = ?";
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, available);
        stmt.setInt(2, bookId);
        int affectedRows = stmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Update failed for " + getTable() + " on id: " + bookId + ", no rows affected.");
        }
    }

    @Override
    protected String getTable() {
        return table;
    }
}
