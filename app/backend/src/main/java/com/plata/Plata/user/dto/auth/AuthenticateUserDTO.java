package com.plata.Plata.user.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticateUserDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
