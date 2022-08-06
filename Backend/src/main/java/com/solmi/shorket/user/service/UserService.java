package com.solmi.shorket.user.service;

import com.solmi.shorket.global.exception.EmailLoginFailedCException;
import com.solmi.shorket.global.exception.UserNotFoundCException;
import com.solmi.shorket.global.exception.UserSignupFailedCException;
import com.solmi.shorket.user.domain.User;
import com.solmi.shorket.user.dto.UserExistRequestDto;
import com.solmi.shorket.user.dto.UserInfoResponseDto;
import com.solmi.shorket.user.dto.UserLoginResponseDto;
import com.solmi.shorket.user.dto.UserSignupRequestDto;
import com.solmi.shorket.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean findUserByEmail(UserExistRequestDto userExistRequestDto) {
        if (userRepository.findByEmail(userExistRequestDto.getEmail()).isPresent())
            return true;
        else
            return false;
    }
}
