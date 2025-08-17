package fu.hanoi.swp.VDShop.util;

import fu.hanoi.swp.VDShop.config.JwtTokenProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Utility class để test JWT token generation
 * Chỉ sử dụng trong development
 */
@Component
public class JwtTestUtil {
    
    private final JwtTokenProvider tokenProvider;
    
    public JwtTestUtil(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }
    
    public void testJwtTokenGeneration() {
        try {
            // Tạo test authentication
            List<SimpleGrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_USER")
            );
            
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                "test@example.com", 
                "password", 
                authorities
            );
            
            // Generate token
            String token = tokenProvider.generateToken(auth, "Test User");
            System.out.println("Generated JWT Token: " + token);
            
            // Validate token
            boolean isValid = tokenProvider.validateToken(token);
            System.out.println("Token is valid: " + isValid);
            
            // Extract username
            String username = tokenProvider.getUsernameFromJWT(token);
            System.out.println("Username from token: " + username);
            
        } catch (Exception e) {
            System.err.println("Error testing JWT: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
