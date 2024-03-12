package com.mocktest.until;
import java.security.NoSuchAlgorithmException;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PasswordEncoderExample {
     public static String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public static boolean checkPassword(String rawPassword, String hashedPasswordFromDb) {
         return BCrypt.checkpw(rawPassword, hashedPasswordFromDb);
    }
    public static boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }
}
