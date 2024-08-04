package presentation.views.cruds;

import core.application.services.BookService;
import core.domain.entities.Book;
import core.domain.views.BaseForm;
import infrastructure.repositories.BookRepository;
import presentation.views.dashboard.Dashboard;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class BookForm extends JFrame implements BaseForm {
    private JTextField publishYearInput;
    private JTextField titleInput;
    private JTextField authoInput;
    private JTextField genreInput;
    private JTextArea descriptionTest;
    private JButton saveButton;
    private JLabel crudBookTitle;
    private JPanel bookFormPanel;
    private boolean isEditMode = false;
    private final BookService _bookService;
    private final Book book = new Book();

    public BookForm() {
        _bookService = new BookService(new BookRepository());
        initForm();
    }

    public BookForm(int id) throws SQLException {
        _bookService = new BookService(new BookRepository());
        isEditMode = true;
        book.setId(id);
        initForm();
        fillFormWithBookData(id);
    }

    private void initForm() {
        setTitle(isEditMode ? "Edit Book" : "Create Book");
        crudBookTitle.setText(isEditMode ? "Edit Book" : "Create Book");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(bookFormPanel);
        setLocationRelativeTo(null);
        addListeners();
        pack();
    }

    private void fillFormWithBookData(int id) throws SQLException {
        Book book = _bookService.getById(id);
        titleInput.setText(book.getTitle());
        authoInput.setText(book.getAuthor());
        publishYearInput.setText(String.valueOf(book.getPublishYear()));
        genreInput.setText(book.getGenre());
        descriptionTest.setText(book.getDescription());
    }

    @Override
    public void addListeners() {
        addWinListener();
        addSaveButtonListener();
    }

    private void addSaveButtonListener() {
        saveButton.addActionListener(e -> {
            getFormData();
            try {
                if (isEditMode) {
                    _bookService.update(book);
                } else {
                    _bookService.create(book);
                }
                dispose();
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
                dashboard.pack();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void getFormData() {
        book.setTitle(titleInput.getText());
        book.setAuthor(authoInput.getText());
        book.setPublishYear(Integer.parseInt(publishYearInput.getText()));
        book.setGenre(genreInput.getText());
        book.setDescription(descriptionTest.getText());
    }

    private void addWinListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Dashboard dashboard = null;
                try {
                    dashboard = new Dashboard();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                dashboard.setVisible(true);
                dashboard.pack();

            }
        });
    }
}
