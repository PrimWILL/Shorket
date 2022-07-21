package com.solmi.shorket.user.dto;

import com.solmi.shorket.user.domain.LoginType;
import com.solmi.shorket.user.domain.RoleType;
import com.solmi.shorket.user.domain.StatusType;
import com.solmi.shorket.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Getter
public class UserSignupRequestDto {

    private String email;
    private String password;
    private String name;
    private String nickName;
    private String profileUrl;
    private LoginType loginType;

    @Builder
    public UserSignupRequestDto(String email, String password, String name,
                                String nickName, String profileUrl, LoginType loginType) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.profileUrl = profileUrl;
        this.loginType = loginType;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickName(nickName)
                .profileUrl(profileUrl)
                .loginType(loginType)
                .build();
    }
}
