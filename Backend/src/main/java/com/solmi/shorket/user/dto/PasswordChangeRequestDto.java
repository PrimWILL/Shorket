package com.solmi.shorket.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeRequestDto {

    private String beforePassword;
    private String afterPassword;
}
