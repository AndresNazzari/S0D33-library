package core.application.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
// https://github.com/patrickfav/bcrypt

public class PasswordService {

    public String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public boolean checkPassword(String candidate, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(candidate.toCharArray(), hashedPassword);
        return result.verified;
    }
}
