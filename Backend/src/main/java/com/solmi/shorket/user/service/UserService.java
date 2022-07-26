package com.solmi.shorket.user.service;

import com.solmi.shorket.global.exception.EmailLoginFailedCException;
import com.solmi.shorket.global.exception.UserSignupFailedCException;
import com.solmi.shorket.user.domain.User;
import com.solmi.shorket.user.dto.UserLoginResponseDto;
import com.solmi.shorket.user.dto.UserSignupRequestDto;
import com.solmi.shorket.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> selectById(Long idx) {
        return userRepository.findById(idx);
    }

    @Transactional
    public Integer signup(UserSignupRequestDto userSignupRequestDto) {
        if (userRepository.findByEmail(userSignupRequestDto.getEmail()).orElse(null) == null)
            return userRepository.save(userSignupRequestDto.toEntity()).getIdx();
        else throw new UserSignupFailedCException();
    }

    public UserLoginResponseDto login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(EmailLoginFailedCException::new);

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new EmailLoginFailedCException();
        return new UserLoginResponseDto(user);
    }
}
