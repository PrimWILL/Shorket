package com.solmi.shorket.user.dto;

import com.solmi.shorket.user.domain.LoginType;
import com.solmi.shorket.user.domain.RoleType;
import com.solmi.shorket.user.domain.User;
import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private final UserTokenDto userToken;
    private final RoleType roleType;

    public UserLoginResponseDto(UserTokenDto userTokenDto, User user) {
        this.userToken = userTokenDto;
        this.roleType = user.getUserRole();
    }
}
