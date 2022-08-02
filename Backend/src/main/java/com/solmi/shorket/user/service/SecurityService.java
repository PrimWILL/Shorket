package com.solmi.shorket.user.service;

import com.solmi.shorket.global.JwtProvider;
import com.solmi.shorket.global.exception.EmailLoginFailedCException;
import com.solmi.shorket.global.exception.UserNotFoundCException;
import com.solmi.shorket.user.domain.User;
import com.solmi.shorket.user.domain.UserToken;
import com.solmi.shorket.user.dto.UserLoginRequestDto;
import com.solmi.shorket.user.dto.UserSignupRequestDto;
import com.solmi.shorket.user.dto.UserTokenDto;
import com.solmi.shorket.user.dto.UserTokenRequestDto;
import com.solmi.shorket.user.repository.UserRepository;
import com.solmi.shorket.user.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserTokenRepository userTokenRepository;

    @Transactional
    public UserTokenDto login(UserLoginRequestDto userLoginRequestDto) {

        // find information of user from DB
        User user = userRepository.findByEmail(userLoginRequestDto.getEmail())
                .orElseThrow(EmailLoginFailedCException::new);

        // check is password equal to DB's encoding password
        if (!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword()))
            throw new EmailLoginFailedCException();

        // issue AccessToken and RefreshToken
        UserTokenDto userTokenDto = jwtProvider.createToken(user.getIdx(), user.getUserRole());

        // save refreshToken
        UserToken userToken = UserToken.builder()
                .user(user)
                .token(userTokenDto.getRefreshToken())
                .build();
        userTokenRepository.save(userToken);
        return userTokenDto;
    }

    @Transactional
    public Integer signup(UserSignupRequestDto userSignupRequestDto) {
        if (userRepository.findByEmail(userSignupRequestDto.getEmail()).isPresent())
            throw new EmailLoginFailedCException();
        return userRepository.save(userSignupRequestDto.toEntity(passwordEncoder)).getIdx();
    }

    @Transactional
    public UserTokenDto reissue(UserTokenRequestDto userTokenRequestDto) {

        // throw error if refresh token is expired
        if (!jwtProvider.validationToken(userTokenRequestDto.getRefreshToken()))
            throw null;

        // get userIdx from
        String accessToken = userTokenRequestDto.getAccessToken();
        Authentication authentication = jwtProvider.getAuthentication(accessToken);

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(UserNotFoundCException::new);
        UserToken userToken = userTokenRepository.findByUserIdx(user.getIdx())
                .orElseThrow(null);

        if (!userToken.getToken().equals(userTokenRequestDto.getRefreshToken()))
            throw null;

        UserTokenDto newToken = jwtProvider.createToken(user.getIdx(), user.getUserRole());
        UserToken updateUserToken = userToken.updateToken(newToken.getRefreshToken());
        userTokenRepository.save(updateUserToken);

        return newToken;
    }
}
