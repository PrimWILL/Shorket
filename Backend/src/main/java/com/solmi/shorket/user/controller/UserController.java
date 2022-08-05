package com.solmi.shorket.user.controller;

import com.solmi.shorket.global.JwtProvider;
import com.solmi.shorket.user.domain.LoginType;
import com.solmi.shorket.user.domain.User;
import com.solmi.shorket.user.dto.*;
import com.solmi.shorket.user.service.SecurityService;
import com.solmi.shorket.user.service.UserService;
import io.swagger.annotations.*;
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

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 받은 AccessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(
            value = "유저 정보 읽기",
            notes = "userIdx를 받아서 해당 번호를 가진 유저의 정보를 읽어온다."
    )
    @GetMapping("/")
    public UserInfoResponseDto getUserInfo(
            @RequestHeader("X-AUTH-TOKEN") String accessToken
    ) {
        return securityService.findUser(accessToken);
    }

    @ApiOperation(
            value = "이메일로 유저 존재 여부 확인",
            notes = "이메일을 받아서 해당 이메일로 가입된 유저가 있는지 조회한다.\n" +
                    "True: 존재함 / False: 존재하지 않음"
    )
    @PostMapping("/email")
    public boolean isUserExist(
            @ApiParam(value = "조회할 이메일 Dto", required = true)
            @RequestBody UserExistRequestDto userExistRequestDto
    ) {
        return userService.findUserByEmail(userExistRequestDto);
    }
}
