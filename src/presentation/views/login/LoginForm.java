package presentation.views.login;

import core.domain.views.BaseForm;


import javax.swing.*;

public class LoginForm extends JFrame implements BaseForm {
    private JPanel loginPanel;
    private JTextField dniInput;
    private JPasswordField passwordInput;
    private JButton loginButton;
    private JLabel dniLabel;
    private JLabel passwordLabel;
    private JLabel titleLabel;

    public LoginForm() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(loginPanel);
        setLocationRelativeTo(null);
        pack();

        addListeners();
    }


    @Override
    public void addListeners() {
        btnListener();
    }

    private void btnListener() {

    }
}
