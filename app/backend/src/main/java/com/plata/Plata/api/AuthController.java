package com.plata.Plata.api;

import com.plata.Plata.api.base.BaseApiController;
import com.plata.Plata.core.configuration.provider.ApiBuilderProvider;
import com.plata.Plata.core.cookie.HttpOnlyAuthCookie;
import com.plata.Plata.core.dto.BaseApiResponseDTO;
import com.plata.Plata.core.enums.AuthenticationType;
import com.plata.Plata.core.exception.TranslatedException;
import com.plata.Plata.core.messages.ApiMessages;
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
public class AuthController extends BaseApiController {
    private final UserService userService;
    private final com.plata.Plata.core.cookie.Cookie httpOnlyAuthCookie;

    public AuthController(UserService userService,
                          ApiBuilderProvider translatedMessageBuilderProvider) {
        super(translatedMessageBuilderProvider);
        this.userService = userService;
        httpOnlyAuthCookie = new HttpOnlyAuthCookie();
    }

    @PostMapping("register")
    public ResponseEntity<BaseApiResponseDTO<UserDTO>> register(@Valid @RequestBody RegisterUserDTO registerUserDTO) throws TranslatedException {
        var user = userService.registerUser(registerUserDTO);

        return ResponseEntity.ok(
                apiBuilderProvider.<UserDTO>builder()
                        .message(ApiMessages.USER_REGISTERED_SUCCESSFULLY.value())
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
