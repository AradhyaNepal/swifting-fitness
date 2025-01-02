package com.a2.swifting_fitness.features.auth.config;

import com.a2.pickyami.game.entity.Players;
import com.a2.pickyami.game.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
class MyUserDetailsService implements UserDetailsService {

    final private PlayerRepository userRepository;

    @Override
    public Players loadUserByUsername(String uid) throws UsernameNotFoundException {
        var user = userRepository.findByUId(uid);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("User not found: ");
        }

    }
}
