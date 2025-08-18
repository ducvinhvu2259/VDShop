package fu.hanoi.swp.VDShop.controller;

import fu.hanoi.swp.VDShop.dto.ApiResponse;
import fu.hanoi.swp.VDShop.dto.auth.request.LoginRequest;
import fu.hanoi.swp.VDShop.dto.auth.request.RegisterRequest;
import fu.hanoi.swp.VDShop.dto.auth.response.AuthResponse;
import fu.hanoi.swp.VDShop.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Allow frontend access
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        ApiResponse<AuthResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Login successful");
        response.setData(authService.login(loginRequest));
        return response;

    }

    @PostMapping("/register")
    public ApiResponse<?> register(@RequestBody RegisterRequest registerRequest) {
        ApiResponse<?> response = new ApiResponse<>();
        response.setSuccess(true);
        authService.register(registerRequest);
        response.setMessage("Register successful");
        return response;
    }
}
