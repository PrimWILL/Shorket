package com.solmi.shorket.user.service;

import com.solmi.shorket.global.exception.UserNotFoundCException;
import com.solmi.shorket.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userIdx) throws UsernameNotFoundException {
        return userRepository.findById(Integer.parseInt(userIdx))
                .orElseThrow(UserNotFoundCException::new);
    }
}
