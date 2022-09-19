package com.solmi.shorket.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserTokenRequestDto {

    String refreshToken;

    @Builder
    public UserTokenRequestDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
