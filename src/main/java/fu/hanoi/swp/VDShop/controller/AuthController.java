package fu.hanoi.swp.VDShop.controller;

import fu.hanoi.swp.VDShop.dto.ApiResponse;
import fu.hanoi.swp.VDShop.dto.auth.request.LoginRequest;
import fu.hanoi.swp.VDShop.dto.auth.response.AuthResponse;
import fu.hanoi.swp.VDShop.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Allow frontend access
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest loginRequest) {
        try {
            AuthResponse authResponse = authService.login(loginRequest);
            ApiResponse<AuthResponse> response = new ApiResponse<>();
            response.setSuccess(true);
            response.setMessage("Login successful");
            response.setData(authResponse);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<AuthResponse> response = new ApiResponse<>();
            response.setSuccess(false);
            response.setMessage("Login failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Auth endpoint is working!");
    }
    
    @PostMapping("/login-simple")
    public ResponseEntity<AuthResponse> loginSimple(@RequestBody LoginRequest loginRequest) {
        try {
            AuthResponse authResponse = authService.login(loginRequest);
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
