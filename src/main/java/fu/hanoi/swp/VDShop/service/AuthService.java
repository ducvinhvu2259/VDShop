package fu.hanoi.swp.VDShop.service;

import fu.hanoi.swp.VDShop.dto.auth.request.LoginRequest;
import fu.hanoi.swp.VDShop.dto.auth.request.RegisterRequest;
import fu.hanoi.swp.VDShop.dto.auth.response.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);

    void register(RegisterRequest registerRequest);
}
