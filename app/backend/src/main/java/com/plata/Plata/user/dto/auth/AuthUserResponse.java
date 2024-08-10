package com.plata.Plata.user.dto.auth;

import com.plata.Plata.core.enums.AuthenticationType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthUserResponse {
    private AuthenticationType type;
    private String token;
}
