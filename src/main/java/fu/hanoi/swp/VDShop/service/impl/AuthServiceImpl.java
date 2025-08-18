package fu.hanoi.swp.VDShop.service.impl;

import fu.hanoi.swp.VDShop.config.JwtTokenProvider;
import fu.hanoi.swp.VDShop.dto.auth.request.LoginRequest;
import fu.hanoi.swp.VDShop.dto.auth.request.RegisterRequest;
import fu.hanoi.swp.VDShop.dto.auth.response.AuthResponse;
import fu.hanoi.swp.VDShop.entity.User;
import fu.hanoi.swp.VDShop.exception.AppException;
import fu.hanoi.swp.VDShop.exception.ErrorCode;
import fu.hanoi.swp.VDShop.repository.UserRepository;
import fu.hanoi.swp.VDShop.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor()
public class AuthServiceImpl implements AuthService {
    JwtTokenProvider tokenProvider;
    UserRepository userRepository;
    AuthenticationManager authenticationManager;
    PasswordEncoder passwordEncoder;
    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            String username = loginRequest.getUsername();
            User user = userRepository.findUserByUsername(username);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);


            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            return AuthResponse.builder()
                    .token(jwt)
                    .username(user.getUsername())
                    .roles(roles)
                    .userId(user.getId())
                    .build();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public void register(RegisterRequest registerRequest) {
        if (userRepository.findUserByEmail(registerRequest.getEmail()) != null)
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        if (userRepository.findUserByUsername(registerRequest.getUsername()) != null)
            throw new AppException(ErrorCode.USERNAME_EXISTED);
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setFisrtName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.saveAndFlush(user);
    }
}
