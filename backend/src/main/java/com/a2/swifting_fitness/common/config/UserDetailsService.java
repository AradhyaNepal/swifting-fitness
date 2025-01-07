package com.a2.swifting_fitness.common.config;

import com.a2.swifting_fitness.features.auth.entity.FitnessFolks;
import com.a2.swifting_fitness.features.auth.repository.FitnessFolksRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
class MyUserDetailsService implements UserDetailsService {

    final private FitnessFolksRepository userRepository;

    @Override
    public FitnessFolks loadUserByUsername(String uid) throws UsernameNotFoundException {
        var user = userRepository.findByUId(uid);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("User not found: ");
        }

    }
}
