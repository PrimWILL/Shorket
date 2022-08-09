package com.solmi.shorket.user.service;

import com.solmi.shorket.global.JwtProvider;
import com.solmi.shorket.global.exception.*;
import com.solmi.shorket.user.domain.StatusType;
import com.solmi.shorket.user.domain.User;
import com.solmi.shorket.user.domain.UserToken;
import com.solmi.shorket.user.dto.*;
import com.solmi.shorket.user.repository.UserRepository;
import com.solmi.shorket.user.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        // throw error if refresh token is expired or not found
        if (!jwtProvider.validationToken(userTokenRequestDto.getRefreshToken()))
            throw new RefreshTokenExpiredCException();

        // get userIdx from AccessToken
        String accessToken = userTokenRequestDto.getAccessToken();

        // find user by using accessToken
        User user = findUserByAccessToken(accessToken);

        // if refresh token is not saved in DB
        UserToken userToken = userTokenRepository.findByUserIdx(user.getIdx())
                .orElseThrow(RefreshTokenNotFoundCException::new);

        // if refresh token is not equal
        if (!userToken.getToken().equals(userTokenRequestDto.getRefreshToken()))
            throw new RefreshTokenNotFoundCException();

        UserTokenDto newToken = jwtProvider.createToken(user.getIdx(), user.getUserRole());
        UserToken updateUserToken = userToken.updateToken(newToken.getRefreshToken());
        userTokenRepository.save(updateUserToken);

        return newToken;
    }

    @Transactional(readOnly = true)
    public UserInfoResponseDto findUser(String accessToken) {
        // find user by using accessToken
        User user = findUserByAccessToken(accessToken);

        // if the user is not an active member of service
        if (!user.getStatusType().equals(StatusType.Y))
            throw new UserNotFoundCException();

        return new UserInfoResponseDto(user);
    }

    @Transactional
    public Integer changePassword(Integer userIdx, PasswordChangeRequestDto passwordChangeRequestDto) {
        // find user by using accessToken
        User user = userRepository.findById(userIdx)
                .orElseThrow(UserNotFoundCException::new);

        // check is password equal to DB's encoding password
        if (!passwordEncoder.matches(passwordChangeRequestDto.getBeforePassword(), user.getPassword()))
            throw new ChangePasswordFailedCException();

        User updateUser = user.updatePassword(passwordEncoder.encode(passwordChangeRequestDto.getAfterPassword()));
        return userRepository.save(updateUser).getIdx();
    }

    @Transactional(readOnly = true)
    public User findUserByAccessToken(String accessToken) {
        // get userIdx from AccessToken
        Authentication authentication = jwtProvider.getAuthentication(accessToken);

        // find user by using userIdx
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(UserNotFoundCException::new);

        return user;
    }
}
