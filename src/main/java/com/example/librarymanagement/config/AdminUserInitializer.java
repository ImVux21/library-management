package com.example.librarymanagement.config;

import com.example.librarymanagement.entities.User;
import com.example.librarymanagement.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdminUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public void run(String... args) {
        String name = "Chinh";
        String email = "admin@gmail.com";
        String password = "123456";

        if (userRepository.findByEmail(email).isEmpty()) {
            User adminUser = User.builder()
                    .name(name)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .role("ADMIN")
                    .build();
            userRepository.save(adminUser);
        }
    }
}