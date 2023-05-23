package com.example.librarymanagement.services.implementations;

import com.example.librarymanagement.dto.request.LoginRequest;
import com.example.librarymanagement.dto.request.RegisterRequest;
import com.example.librarymanagement.dto.response.LoginResponse;
import com.example.librarymanagement.dto.response.Response;
import com.example.librarymanagement.dto.response.UserInfoResponse;
import com.example.librarymanagement.entities.User;
import com.example.librarymanagement.repositories.UserRepository;
import com.example.librarymanagement.services.AuthService;
import com.example.librarymanagement.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<Response> register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Người dùng đã được đăng ký!");
        }

        User user = User
                .builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role("USER")
                .build();

        return ResponseEntity.ok(
                Response
                        .builder()
                        .status(200)
                        .message("Đăng ký thành công!")
                        .data(userRepository.save(user))
                        .build()
        );
    }

    @Override
    public ResponseEntity<Response> login(LoginRequest loginRequest) {
        if (!userRepository.existsByEmail(loginRequest.getEmail())) {
            throw new RuntimeException("Email chưa được đăng ký!");
        }

        User user = userRepository.getByEmail(loginRequest.getEmail());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException("Sai mật khẩu!");
        }

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(
                Response
                        .builder()
                        .status(200)
                        .message("Đăng nhập thành công!")
                        .data(LoginResponse.builder()
                                .token(token)
                                .name(user.getName())
                                .email(user.getEmail())
                                .role(user.getRole())
                                .build()
                        )
                        .build()
        );
    }

    @Override
    public ResponseEntity<Response> getUserInfo(User user) {

        return ResponseEntity.ok(
                Response
                        .builder()
                        .status(200)
                        .data(UserInfoResponse
                                .builder()
                                .name(user.getName())
                                .email(user.getEmail())
                                .role(user.getRole())
                                .build()
                        )
                        .message("Đăng nhập thành công")
                        .build()
        );
    }
}
