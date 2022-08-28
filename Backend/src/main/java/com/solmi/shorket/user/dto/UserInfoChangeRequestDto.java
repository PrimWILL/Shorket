package com.solmi.shorket.user.dto;

import com.solmi.shorket.user.domain.LoginType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoChangeRequestDto {

    private String email;
    private String name;
    private String nickName;
    private String profileUrl;

}
