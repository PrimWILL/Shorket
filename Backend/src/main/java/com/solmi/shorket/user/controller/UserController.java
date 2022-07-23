package com.solmi.shorket.user.controller;

import com.solmi.shorket.global.JwtProvider;
import com.solmi.shorket.user.domain.LoginType;
import com.solmi.shorket.user.domain.User;
import com.solmi.shorket.user.dto.UserLoginResponseDto;
import com.solmi.shorket.user.dto.UserSignupRequestDto;
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
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "로그인")
    @GetMapping("/login")
    public String login(
            @ApiParam(value="로그인 아이디: 이메일", required = true) @RequestParam String email,
            @ApiParam(value="로그인 비밀번호", required = true) @RequestParam String password
    ) {
        UserLoginResponseDto userLoginDto = userService.login(email, password);

        String token = jwtProvider.createToken(String.valueOf(userLoginDto.getUserIdx()), userLoginDto.getLoginType());
        return token;
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public Integer signup(
            @ApiParam(value="회원가입 아이디: 이메일", required = true) @RequestParam String email,
            @ApiParam(value="회원가입 비밀번호", required = true) @RequestParam String password,
            @ApiParam(value="회원가입 이름", required = true) @RequestParam String name,
            @ApiParam(value="회원가입 닉네임", required = true) @RequestParam String nickName,
            @ApiParam(value="회원가입 유형", required = true) @RequestParam LoginType loginType
            ) {
        UserSignupRequestDto userSignupRequestDto = UserSignupRequestDto.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .nickName(nickName)
                .loginType(loginType)
                .build();
        Integer userIdx = userService.signup(userSignupRequestDto);
        return userIdx;
    }
}
