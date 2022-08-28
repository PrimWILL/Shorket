package com.solmi.shorket.user.dto;

import com.solmi.shorket.user.domain.LoginType;
import com.solmi.shorket.user.domain.User;
import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private final Integer userIdx;
    private final LoginType loginType;

    public UserLoginResponseDto(User user) {
        this.userIdx = user.getIdx();
        this.loginType = user.getLoginType();
    }
}
