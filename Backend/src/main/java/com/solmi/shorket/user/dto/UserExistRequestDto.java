package com.solmi.shorket.user.dto;

import com.solmi.shorket.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserExistRequestDto {

    private String email;

}
