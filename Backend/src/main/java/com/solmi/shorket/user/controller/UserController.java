package com.solmi.shorket.user.controller;

import com.solmi.shorket.user.dto.*;
import com.solmi.shorket.user.service.SecurityService;
import com.solmi.shorket.user.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
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
    public UserLoginResponseDto login(
            @ApiParam(value = "로그인 요청 DTO", required = true)
            @RequestBody UserLoginRequestDto userLoginRequestDto
    ) {
        UserLoginResponseDto userLoginResponseDto = securityService.login(userLoginRequestDto);
        return userLoginResponseDto;
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public Integer signup(
            @ApiParam(value = "회원가입 요청 DTO", required = true)
            @RequestBody UserSignupRequestDto userSignupRequestDto
    ) {
        Integer userIdx = securityService.signup(userSignupRequestDto);
        return userIdx;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 받은 AccessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(
            value = "액세스, 리프레시 토큰 재발급",
            notes = "accessToken 만료 시 회원 검증 후 refreshToken을 검증해서 accessToken과 refreshToken을 재발급한다."
    )
    @PostMapping("/reissue")
    public UserTokenDto reissue(
            @RequestHeader("X-AUTH-TOKEN") String accessToken,
            @ApiParam(value = "토큰 재발급 요청 DTO", required = true)
            @RequestBody UserTokenRequestDto userTokenRequestDto
    ) {
        return securityService.reissue(accessToken, userTokenRequestDto);
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
            notes = "accessToken을 받아서 해당 번호를 가진 유저의 정보를 읽어온다."
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
        return userService.existsUserByEmail(userExistRequestDto);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 발급받은 accessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(
            value = "비밀번호 변경",
            notes = "query parameter로 전달받은 유저의 기존 비밀번호가 DB와 일치하는지 확인 후 비밀번호를 변경한다.\n" +
                    "로그인한 유저만 접근할 수 있게 accessToken 여부를 확인한다."
    )
    @PutMapping("/{userIdx}/password")
    public void changePassword(
            @ApiParam(value = "유저의 인덱스") @PathVariable Integer userIdx,
            @ApiParam(value = "비밀번호 변경 요청 DTO", required = true)
            @RequestBody PasswordChangeRequestDto passwordChangeRequestDto
    ) {
        securityService.changePassword(userIdx, passwordChangeRequestDto);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 발급받은 accessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(
            value = "유저 정보 변경",
            notes = "accessToken으로 전달받은 유저가 실제 유저인지 확인한 후, 유저의 정보를 변경한다.\n"
    )
    @PutMapping("/")
    public void changeUserInfo(
            @RequestHeader("X-AUTH-TOKEN") String accessToken,
            @ApiParam(value = "유저 정보 변경 요청 DTO", required = true)
            @RequestBody UserInfoChangeRequestDto userInfoChangeRequestDto
    ) {
        securityService.updateUserInfo(accessToken, userInfoChangeRequestDto);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 발급받은 accessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(
            value = "로그아웃",
            notes = "accessToken으로 전달받은 유저가 실제 유저인지 확인한 후, 해당 유저를 서비스에서 로그아웃시킨다.\n"
    )
    @PostMapping("/logout")
    public void logout(
            @RequestHeader("X-AUTH-TOKEN") String accessToken
    ) {
        securityService.logout(accessToken);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "X-AUTH-TOKEN",
                    value = "로그인 성공 후 발급받은 accessToken",
                    required = true, dataType = "String", paramType = "header"
            )
    })
    @ApiOperation(
            value = "회원탈퇴",
            notes = "accessToken으로 전달받은 유저가 실제 유저인지 확인한 후, 해당 유저를 서비스에서 탈퇴시킨다.\n"
    )
    @DeleteMapping("/withdraw")
    public void withdraw(
            @RequestHeader("X-AUTH-TOKEN") String accessToken
    ) {
        securityService.deleteUser(accessToken);
    }
}
