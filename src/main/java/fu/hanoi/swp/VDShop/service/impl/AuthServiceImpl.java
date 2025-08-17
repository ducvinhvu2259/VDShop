package fu.hanoi.swp.VDShop.service.impl;

import fu.hanoi.swp.VDShop.config.JwtTokenProvider;
import fu.hanoi.swp.VDShop.dto.auth.request.LoginRequest;
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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor()
public class AuthServiceImpl implements AuthService {
    JwtTokenProvider tokenProvider;
    UserRepository userRepository;
    AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            String email = loginRequest.getUsername();
            User user = userRepository.findUserByEmail(email);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication, user.getFullName());


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
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
    }
}
