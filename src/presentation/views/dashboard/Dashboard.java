package presentation.views.dashboard;

import core.application.services.BookService;
import core.application.services.LendService;
import core.application.services.UserService;
import core.domain.entities.Book;
import core.domain.entities.Lend;
import core.domain.entities.User;
import core.domain.views.BaseForm;
import infrastructure.repositories.BookRepository;
import infrastructure.repositories.LendRepository;
import infrastructure.repositories.UserRepository;
import presentation.views.cruds.BookForm;
import presentation.views.cruds.StudentForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Dashboard extends JFrame implements BaseForm {
    private JButton createStudentsButton;
    private JButton createLendButton;
    private JTable studentsTable;
    private JTable booksTable;
    private JButton createBookButton;
    private JButton editSelectedBookButton;
    private JButton deleteSelectedStudentButton;
    private JButton editSelectedStudentButton;
    private JButton deleteSelectedBookButton;
    private JPanel dashboard;
    private JButton returnSelectedLendButton;
    private JButton editSelectedLendButton;
    private JTable lendsTable;
    private JLabel totalLendsLabel;
    private JLabel totalBooksLabel;
    private JLabel totalStudentsLabel;
    private JLabel popularStudentLabel;
    private JLabel popularBookLabel;

    private final BookService _bookService;
    private final UserService _userService;
    private final LendService _lendService;

    public Dashboard() throws SQLException {
        _bookService = new BookService(new BookRepository());
        _userService = new UserService(new UserRepository());
        _lendService = new LendService(new LendRepository());

        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(dashboard);
        //setLocationRelativeTo(null);
        pack();

        addListeners();
        loadData();
    }

    @Override
    public void addListeners() {
        addCreateBookButtonListener();
        addEditSelectedBookButtonListener();
        addDeleteBookButtonListener();
        addCreateStudentsButtonListener();
        addEditSelectedStudentButtonListener();
        addDeleteStudentButtonListener();
        addCreateLendButtonListener();
        addEditSelectedLendButtonListener();
        addReturnSelectedLendButtonListener();
    }

    private void addCreateBookButtonListener() {
        createBookButton.addActionListener(e -> {
            dispose();
            clearTableSelections();

            BookForm bookForm = new BookForm();
            bookForm.setVisible(true);
            bookForm.pack();
        });
    }

    private void addEditSelectedBookButtonListener() {
        editSelectedBookButton.addActionListener(e -> {
            int selectedRow = booksTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a book to edit.");
                return;
            }

            int bookId = (int) booksTable.getValueAt(selectedRow, 0);
            dispose();
            clearTableSelections();

            BookForm bookForm = null;
            try {
                bookForm = new BookForm(bookId);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            bookForm.setVisible(true);
            bookForm.pack();
        });
    }

    private void addDeleteBookButtonListener() {
        deleteSelectedBookButton.addActionListener(e -> {
            int selectedRow = booksTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a book to delete.");
                return;
            }

            int bookId = (int) booksTable.getValueAt(selectedRow, 0);
            try {
                _bookService.delete(bookId);

                loadBooks();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });
    }

    private void addCreateStudentsButtonListener() {
        createStudentsButton.addActionListener(e -> {
            dispose();
            clearTableSelections();

            StudentForm studentForm = new StudentForm();
            studentForm.setVisible(true);
            studentForm.pack();
        });
    }

    private void addEditSelectedStudentButtonListener() {
        editSelectedStudentButton.addActionListener(e -> {
            int selectedRow = studentsTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a student to edit.");
                return;
            }

            int studentId = (int) studentsTable.getValueAt(selectedRow, 0);
            dispose();
            clearTableSelections();

            StudentForm studentForm = null;
            try {
                studentForm = new StudentForm(studentId);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            studentForm.setVisible(true);
            studentForm.pack();
        });
    }

    private void addDeleteStudentButtonListener() {
        deleteSelectedStudentButton.addActionListener(e -> {
            int selectedRow = studentsTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a student to delete.");
                return;
            }

            int studentId = (int) studentsTable.getValueAt(selectedRow, 0);
            try {
                _userService.delete(studentId);

                loadStudents();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });
    }

    private void addCreateLendButtonListener() {
        createLendButton.addActionListener(e -> {
            int selectedStudentRow = studentsTable.getSelectedRow();
            int selectedBookRow = booksTable.getSelectedRow();

            if (selectedStudentRow == -1 || selectedBookRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a student and a book to lend.");
                return;
            }

            int studentId = (int) studentsTable.getValueAt(selectedStudentRow, 0);
            String studentName = studentsTable.getValueAt(selectedStudentRow, 1) + " " + studentsTable.getValueAt(selectedStudentRow, 2);
            int bookId = (int) booksTable.getValueAt(selectedBookRow, 0);
            String bookTitle = (String) booksTable.getValueAt(selectedBookRow, 1);
            showLendCreationDialog(studentId, studentName, bookId, bookTitle);

            try {
                loadData();
                clearTableSelections();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void showLendCreationDialog(int studentId, String studentName, int bookId, String bookTitle) {
        JTextField dateField = new JTextField();
        final JComponent[] inputs = new JComponent[]{
                new JLabel("Student ID: " + studentId),
                new JLabel("Student Name: " + studentName),
                new JLabel("Book ID: " + bookId),
                new JLabel("Book Title: " + bookTitle),
                new JLabel("Return Date (yyyy-mm-dd):"),
                dateField
        };

        int result = JOptionPane.showConfirmDialog(null, inputs, "Create Lend", JOptionPane.DEFAULT_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String dateString = dateField.getText();
                _lendService.lendBook(studentId, bookId, dateString);
                _bookService.updateAvailability(bookId, 0);

                JOptionPane.showMessageDialog(null, "Lend created successfully!");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Failed to create lend. Please check your inputs and try again.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addEditSelectedLendButtonListener() {
        editSelectedLendButton.addActionListener(e -> {
            int selectedRow = lendsTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a lend to edit.");
                return;
            }

            int lendId = (int) lendsTable.getValueAt(selectedRow, 0);
            int studentId = (int) lendsTable.getValueAt(selectedRow, 3);
            String studentName = lendsTable.getValueAt(selectedRow, 4) + " " + lendsTable.getValueAt(selectedRow, 5);

            int bookId = (int) lendsTable.getValueAt(selectedRow, 6);
            String bookTitle = (String) lendsTable.getValueAt(selectedRow, 7);

            showLendEditDialog(lendId, studentId, studentName, bookId, bookTitle);

            try {
                loadData();
                clearTableSelections();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void showLendEditDialog(int lendId, int studentId, String studentName, int bookId, String bookTitle) {
        JTextField dateField = new JTextField();
        final JComponent[] inputs = new JComponent[]{
                new JLabel("Student ID: " + studentId),
                new JLabel("Student Name: " + studentName),
                new JLabel("Book ID: " + bookId),
                new JLabel("Book Title: " + bookTitle),
                new JLabel("Update Return Date (yyyy-mm-dd):"),
                dateField
        };

        int result = JOptionPane.showConfirmDialog(null, inputs, "Edit Lend Return Date", JOptionPane.DEFAULT_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String dateString = dateField.getText();
                _lendService.updateDueDate(lendId, dateString);
                JOptionPane.showMessageDialog(null, "Lend created successfully!");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Failed to create lend. Please check your inputs and try again.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addReturnSelectedLendButtonListener() {
        returnSelectedLendButton.addActionListener(e -> {
            int selectedRow = lendsTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a lend to return.");
                return;
            }

            int lendId = (int) lendsTable.getValueAt(selectedRow, 0);
            int bookId = (int) lendsTable.getValueAt(selectedRow, 6);
            try {
                _lendService.returnLend(lendId);
                _bookService.updateAvailability(bookId, 1);
                JOptionPane.showMessageDialog(null, "Lend returned successfully!");
                loadData();
                clearTableSelections();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void loadData() throws SQLException {
        loadBooks();
        loadStudents();
        loadLends();
    }

    private void loadBooks() throws SQLException {
        List<Book> books = _bookService.getAll();
        String popularBook = _bookService.getPopularBook();
        booksTable.setModel(_bookService.booksToTableModel(books));
        totalBooksLabel.setText(String.valueOf(books.size()));
        popularBookLabel.setText(popularBook);
    }

    private void loadStudents() throws SQLException {
        List<User> users = _userService.getAllStudents();
        String popularStudent = _userService.getPopularUser();
        studentsTable.setModel(_userService.usersToTableModel(users));
        totalStudentsLabel.setText(String.valueOf(users.size()));
        popularStudentLabel.setText(popularStudent);
    }

    private void loadLends() throws SQLException {
        List<Lend> lends = _lendService.getAll();
        lendsTable.setModel(_lendService.booksToTableModel(lends));
        totalLendsLabel.setText(String.valueOf(lends.size()));
    }

    private void clearTableSelections() {
        booksTable.clearSelection();
        studentsTable.clearSelection();
        lendsTable.clearSelection();
    }
}
