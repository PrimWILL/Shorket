package com.solmi.shorket.user.dto;

import com.solmi.shorket.user.domain.LoginType;
import com.solmi.shorket.user.domain.RoleType;
import com.solmi.shorket.user.domain.StatusType;
import com.solmi.shorket.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupRequestDto {

    private String email;
    private String password;
    private String name;
    private String nickName;
    private LoginType loginType;

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .nickName(nickName)
                .profileUrl("")
                .loginType(loginType)
                .statusType(StatusType.Y)
                .userRole(RoleType.I)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .nickName(nickName)
                .loginType(loginType)
                .statusType(StatusType.Y)
                .userRole(RoleType.I)
                .build();
    }
}
