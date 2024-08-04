package presentation.views.cruds;

import core.application.services.UserService;
import core.domain.entities.User;
import core.domain.views.BaseForm;
import infrastructure.repositories.UserRepository;
import presentation.views.dashboard.Dashboard;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class StudentForm extends JFrame implements BaseForm {
    private JLabel crudStudentTitle;
    private JTextField firstNameInput;
    private JTextField lastNameInput;
    private JButton saveButton;
    private JPanel studentFormPanel;
    private boolean isEditMode = false;
    private final UserService _userService;
    private final User user = new User();

    public StudentForm() {
        _userService = new UserService(new UserRepository());
        initForm();
    }

    public StudentForm(int id) throws SQLException {
        _userService = new UserService(new UserRepository());
        isEditMode = true;
        user.setId(id);
        initForm();
        fillFormWithBookData(id);
    }

    private void initForm() {
        setTitle(isEditMode ? "Edit Student" : "Create Student");
        crudStudentTitle.setText(isEditMode ? "Edit Student" : "Create Student");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(studentFormPanel);
        setLocationRelativeTo(null);
        addListeners();
        pack();
    }

    private void fillFormWithBookData(int id) throws SQLException {
        User user = _userService.getById(id);
        firstNameInput.setText(user.getFirstName());
        lastNameInput.setText(user.getLastName());
    }

    private void getFormData() {
        user.setFirstName(firstNameInput.getText());
        user.setLastName(lastNameInput.getText());
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
                    _userService.update(user);
                } else {
                    _userService.create(user);
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
