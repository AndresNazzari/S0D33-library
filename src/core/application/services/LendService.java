package core.application.services;

import core.domain.entities.Lend;
import infrastructure.repositories.LendRepository;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.util.List;

public class LendService {
    private final LendRepository _lendRepository;

    public LendService(LendRepository lendRepository) {
        _lendRepository = lendRepository;
    }

    public List<Lend> getAll() throws SQLException {
        return _lendRepository.getAll();
    }

    public String lendBook(int userId, int bookId, String dueDate) throws SQLException {
        return _lendRepository.lendBook(userId, bookId, dueDate);
    }

    public String returnLend(int lendId) throws SQLException {
        return _lendRepository.returnLend(lendId);
    }

    public String updateDueDate(int id, String dueDate) throws SQLException {
        return _lendRepository.updateDueDate(id, dueDate);
    }

    public TableModel booksToTableModel(List<Lend> lends) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Lend ID");
        model.addColumn("Lend Date");
        model.addColumn("Due Date");
        model.addColumn("User ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Book ID");
        model.addColumn("Title");

        for (Lend lend : lends) {
            model.addRow(new Object[]{
                    lend.getId(),
                    lend.getLendDate(),
                    lend.getDueDate(),
                    lend.getUser().getId(),
                    lend.getUser().getFirstName(),
                    lend.getUser().getLastName(),
                    lend.getBook().getId(),
                    lend.getBook().getTitle()
            });
        }
        return model;
    }
}
