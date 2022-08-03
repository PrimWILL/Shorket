package com.solmi.shorket.user.dto;

import com.solmi.shorket.user.domain.LoginType;
import com.solmi.shorket.user.domain.RoleType;
import com.solmi.shorket.user.domain.StatusType;
import com.solmi.shorket.user.domain.User;
import lombok.Getter;

@Getter
public class UserInfoResponseDto {

    private final Integer userIdx;
    private final String email;
    private final String name;
    private final String nickName;
    private final String profileUrl;
    private final LoginType loginType;
    private final RoleType userRole;
    private final StatusType statusType;

    public UserInfoResponseDto(User user) {
        this.userIdx = user.getIdx();
        this.email = user.getEmail();
        this.name = user.getName();
        this.nickName = user.getNickName();
        this.profileUrl = user.getProfileUrl();
        this.loginType = user.getLoginType();
        this.userRole = user.getUserRole();
        this.statusType = user.getStatusType();
    }
}
