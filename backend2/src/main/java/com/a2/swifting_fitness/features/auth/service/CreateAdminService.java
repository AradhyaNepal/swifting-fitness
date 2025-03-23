package com.a2.swifting_fitness.features.auth.service;

import com.a2.swifting_fitness.common.enums.UserRole;
import com.a2.swifting_fitness.common.enums.UserTier;
import com.a2.swifting_fitness.features.auth.entity.Users;
import com.a2.swifting_fitness.features.auth.repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAdminService {
        private final UsersRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        // This will run once the Spring context is fully initialized
        @PostConstruct
        public void createSuperAdmin() {
            var  email="superadmin@yopmail.com";
            if (userRepository.findByEmail(email).isEmpty()) {
                userRepository.save(
                        Users.builder()
                                .email(email)
                                .firstName("Super")
                                .lastName("Admin")
                                .userTier(UserTier.PRO)
                                .role(UserRole.admin)
                                .password(passwordEncoder.encode("Test@123"))
                                .accountVerified(false)
                                .build()
                );
                System.out.println("Super Admin created successfully!");
            }
        }

}
