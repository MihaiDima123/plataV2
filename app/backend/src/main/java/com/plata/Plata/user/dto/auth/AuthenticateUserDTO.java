package com.plata.Plata.user.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthenticateUserDTO {
    @NotBlank
    @SuppressWarnings("unused")
    private String username;

    @NotBlank
    @SuppressWarnings("unused")
    private String password;
}
