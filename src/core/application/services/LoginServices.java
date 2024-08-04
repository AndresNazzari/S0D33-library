package core.application.services;

public class LoginServices {
    public static String loginValidations(String username, String password) {
        String message = "";
        if (username.isEmpty() || password.isEmpty()) {
            return "Please fill all fields";
        }

        if (username.length() < 6) {
            return "DNI must have at least 6 characters";
        }

        if (password.length() < 4) {
            return "Password must have at least 4 characters";
        }

        // Validación para asegurar que el password solo contenga caracteres permitidos
        if (!password.matches("[a-zA-Z0-9@\\-_]*")) {
            return "Password may only contain alphanumeric characters and @, -, _";
        }

        return message;
    }
}
