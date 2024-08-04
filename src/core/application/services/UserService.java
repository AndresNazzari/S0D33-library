package core.application.services;

import core.domain.entities.User;
import infrastructure.repositories.UserRepository;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserRepository _userRepository;

    public UserService(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    public List<User> getAllStudents() throws SQLException {
        return _userRepository.getAllStudents();
    }

    public String getPopularUser() throws SQLException {
        return _userRepository.getPopularUser();
    }

    public User getUserByUsername(String username) throws SQLException {
        return _userRepository.getUserByUsername(username);
    }

    public User getById(int id) throws SQLException {
        return _userRepository.getById(id);
    }

    public void create(User user) throws SQLException {
        _userRepository.create(user);
    }

    public void update(User user) throws SQLException {
        _userRepository.update(user);
    }

    public void delete(int id) throws SQLException {
        _userRepository.delete(id);
    }


    public TableModel usersToTableModel(List<User> users) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("User ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");

        for (User user : users) {
            model.addRow(new Object[]{
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName()
            });
        }
        return model;
    }
}
