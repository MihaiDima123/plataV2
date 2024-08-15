package com.plata.Plata.api;

import com.plata.Plata.core.cookie.HttpOnlyAuthCookie;
import com.plata.Plata.core.dto.BaseApiResponseDTO;
import com.plata.Plata.core.enums.AuthenticationType;
import com.plata.Plata.core.exception.TranslatedException;
import com.plata.Plata.user.dto.UserDTO;
import com.plata.Plata.user.dto.auth.AuthenticateUserDTO;
import com.plata.Plata.user.dto.auth.RegisterUserDTO;
import com.plata.Plata.user.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final com.plata.Plata.core.cookie.Cookie httpOnlyAuthCookie;

    public AuthController(UserService userService) {
        this.userService = userService;
        httpOnlyAuthCookie = new HttpOnlyAuthCookie();
    }

    @PostMapping("register")
    public ResponseEntity<BaseApiResponseDTO<UserDTO>> register(@Valid @RequestBody RegisterUserDTO registerUserDTO) throws TranslatedException {
        var user = userService.registerUser(registerUserDTO);

        return ResponseEntity.ok(
                BaseApiResponseDTO.<UserDTO>builder()
                        .message("User registered successfully")
                        .data(UserDTO.from(user))
                        .build()
        );
    }

    @PostMapping("login")
    public ResponseEntity<BaseApiResponseDTO<?>> login(
            @Valid @RequestBody AuthenticateUserDTO authenticateUserDTO,
            HttpServletResponse servletResponse
    ) throws TranslatedException {
        var authResponse = userService.authenticateUser(authenticateUserDTO);

        if (authResponse.getType()!= null && authResponse.getType().equals(AuthenticationType.COOKIE)) {
            servletResponse.addCookie(getHttpOnlyAuthCookie(authResponse.getToken()));
        }

        return ResponseEntity.ok().body(null);
    }

    private Cookie getHttpOnlyAuthCookie(String token) {
        return httpOnlyAuthCookie.createNewCookie(token);
    }

}
