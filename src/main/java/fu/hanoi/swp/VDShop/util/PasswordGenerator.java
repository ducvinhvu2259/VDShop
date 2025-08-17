package fu.hanoi.swp.VDShop.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utility class để generate password hash cho testing
 * Chỉ sử dụng trong development, không sử dụng trong production
 */
public class PasswordGenerator {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        
        // Generate hash cho password "12345678"
        String password = "12345678";
        String hashedPassword = encoder.encode(password);
        
        System.out.println("Password: " + password);
        System.out.println("Hashed Password: " + hashedPassword);
        
        // Verify password
        boolean matches = encoder.matches(password, hashedPassword);
        System.out.println("Password matches: " + matches);
        
        // Generate hash cho password khác
        String password2 = "admin123";
        String hashedPassword2 = encoder.encode(password2);
        
        System.out.println("\nPassword: " + password2);
        System.out.println("Hashed Password: " + hashedPassword2);
    }
}
