package presentation.views.login;

import core.application.services.LoginServices;
import core.application.services.PasswordService;
import core.application.services.UserService;
import core.domain.entities.User;
import core.domain.views.BaseForm;
import infrastructure.repositories.UserRepository;
import presentation.views.dashboard.Dashboard;

import javax.swing.*;
import java.sql.SQLException;

public class LoginForm extends JFrame implements BaseForm {
    private JPanel loginPanel;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JButton loginBtn;
    private JLabel usernameLbl;
    private JLabel passwordLbl;
    private JLabel titleLabel;
    private final UserService _userService;
    private final UserRepository _userRepository;
    private final PasswordService _passwordService;

    public LoginForm() {
        _userRepository = new UserRepository();
        _userService = new UserService(_userRepository);
        _passwordService = new PasswordService();

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(loginPanel);
        setLocationRelativeTo(null);
        pack();

        addListeners();
    }

    public void addListeners() {
        btnListener();
    }

    private void btnListener() {
        loginBtn.addActionListener(_ -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();

            String ErrorMessage = LoginServices.loginValidations(username, password);
            if (!ErrorMessage.isEmpty()) {
                JOptionPane.showMessageDialog(null, ErrorMessage);
                return;
            }

            try {
                User user = _userService.getUserByUsername(username);

                boolean loginSuccess = user != null && _passwordService.checkPassword(password, user.getPassword());

                if (loginSuccess) {
                    goToDashboard();
                } else {
                    JOptionPane.showMessageDialog(null, "User does not exist or password is incorrect.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
        });
    }

    public void goToDashboard() throws SQLException {
        dispose();
        Dashboard dashboard = new Dashboard();
        dashboard.setVisible(true);
        dashboard.pack();
    }
}
