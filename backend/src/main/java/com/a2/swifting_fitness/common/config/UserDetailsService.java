package com.a2.swifting_fitness.common.config;

import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.features.auth.entity.FitnessFolks;
import com.a2.swifting_fitness.features.auth.repository.FitnessFolksRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    final private FitnessFolksRepository userRepository;

    @Override
    public FitnessFolks loadUserByUsername(String uid) throws UsernameNotFoundException {
        var user = userRepository.findByUId(uid);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException(StringConstants.userNotFound);
        }

    }
}
