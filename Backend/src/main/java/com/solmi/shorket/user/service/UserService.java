package com.solmi.shorket.user.service;

import com.solmi.shorket.global.exception.UserNotFoundCException;
import com.solmi.shorket.user.domain.User;
import com.solmi.shorket.user.dto.UserExistRequestDto;
import com.solmi.shorket.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findByIdx(Integer idx) {
        return userRepository.findById(idx)
                .orElseThrow(UserNotFoundCException::new);
    }

    public boolean existsUserByEmail(UserExistRequestDto userExistRequestDto) {
        return userRepository.existsByEmail(userExistRequestDto.getEmail());
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundCException::new);
    }
}
