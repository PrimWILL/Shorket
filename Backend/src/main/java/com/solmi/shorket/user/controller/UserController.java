package com.solmi.shorket.user.controller;

import com.solmi.shorket.global.JwtProvider;
import com.solmi.shorket.user.domain.LoginType;
import com.solmi.shorket.user.domain.User;
import com.solmi.shorket.user.dto.*;
import com.solmi.shorket.user.service.SecurityService;
import com.solmi.shorket.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Users")
@RequiredArgsConstructor
@RequestMapping(path = "/api/users")
@RestController
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public UserTokenDto login(
            @ApiParam(value="로그인 요청 DTO", required = true)
            @RequestBody UserLoginRequestDto userLoginRequestDto
            ) {
        UserTokenDto userTokenDto = securityService.login(userLoginRequestDto);
        return userTokenDto;
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public Integer signup(
            @ApiParam(value="회원가입 요청 DTO", required = true)
            @RequestBody UserSignupRequestDto userSignupRequestDto
            ) {
        Integer userIdx = securityService.signup(userSignupRequestDto);
        return userIdx;
    }

    @ApiOperation(
            value = "액세스, 리프레시 토큰 재발급",
            notes = "accessToken 만료 시 회원 검증 후 refreshToken을 검증해서 accessToken과 refreshToken을 재발급한다."
    )
    @PostMapping("/reissue")
    public UserTokenDto reissue(
            @ApiParam(value = "토큰 재발급 요청 DTO", required = true)
            @RequestBody UserTokenRequestDto userTokenRequestDto
            ) {
        return securityService.reissue(userTokenRequestDto);
    }
}
