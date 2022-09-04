package com.solmi.shorket.user.service;

import com.solmi.shorket.user.dto.UserExistRequestDto;
import com.solmi.shorket.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public boolean findUserByEmail(UserExistRequestDto userExistRequestDto) {
        if (userRepository.findByEmail(userExistRequestDto.getEmail()).isPresent())
            return true;
        else
            return false;
    }
}
