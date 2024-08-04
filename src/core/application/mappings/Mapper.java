package core.application.mappings;

import core.domain.entities.Book;
import core.domain.entities.Lend;
import core.domain.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mapper {

    public static User DbUserToUser(ResultSet rs) throws SQLException {

        return new User(
                rs.getInt("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("userName"),
                rs.getString("password"),
                rs.getInt("isAdmin")
        );
    }

    public static Book DbBookToBook(ResultSet rs) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getInt("publishYear"),
                rs.getString("description"),
                rs.getString("author"),
                rs.getString("genre")
        );
    }

    public static Lend DbLendToLend(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("bookId"));
        book.setTitle(rs.getString("title"));

        User user = new User();
        user.setId(rs.getInt("userId"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));

        Lend lend = new Lend();
        lend.setId(rs.getInt("id"));
        lend.setBook(book);
        lend.setUser(user);
        lend.setLendDate(rs.getString("lendDate"));
        lend.setDueDate(rs.getString("dueDate"));

        return lend;
    }
}