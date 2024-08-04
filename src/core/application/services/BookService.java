package core.application.services;

import core.domain.entities.Book;
import infrastructure.repositories.BookRepository;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.util.List;

public class BookService {
    private final BookRepository _bookRepository;

    public BookService(BookRepository bookRepository) {
        _bookRepository = bookRepository;
    }

    public List<Book> getAll() throws SQLException {
        return _bookRepository.getAll();
    }

    public Book getById(int id) throws SQLException {
        return _bookRepository.getById(id);
    }

    public String getPopularBook() throws SQLException {
        return _bookRepository.getPopularBook();
    }

    public void create(Book book) throws SQLException {
        _bookRepository.create(book);
    }

    public void update(Book book) throws SQLException {
        _bookRepository.update(book);
    }

    public void updateAvailability(int bookId, int isAvailable) throws SQLException {
        _bookRepository.updateAvailability(bookId, isAvailable);
    }

    public TableModel booksToTableModel(List<Book> books) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Book ID");
        model.addColumn("Title");
        model.addColumn("Author");
        model.addColumn("Publish Year");
        model.addColumn("Genre");

        for (Book book : books) {
            model.addRow(new Object[]{
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublishYear(),
                    book.getGenre()
            });
        }
        return model;
    }

    public void delete(int bookId) throws SQLException {
        _bookRepository.delete(bookId);
    }
}
