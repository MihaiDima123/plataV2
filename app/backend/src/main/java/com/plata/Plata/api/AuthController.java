package com.plata.Plata.api;

import com.plata.Plata.core.dto.BaseApiResponseDTO;
import com.plata.Plata.core.exception.TranslatedException;
import com.plata.Plata.user.dto.UserDTO;
import com.plata.Plata.user.dto.auth.AuthUserResponse;
import com.plata.Plata.user.dto.auth.AuthenticateUserDTO;
import com.plata.Plata.user.dto.auth.RegisterUserDTO;
import com.plata.Plata.user.services.UserService;
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

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<BaseApiResponseDTO<UserDTO>> register(@Valid @RequestBody RegisterUserDTO registerUserDTO) throws TranslatedException {
        var user = userService.registerUser(registerUserDTO);

        var response = new BaseApiResponseDTO<UserDTO>();
        response.setMessage("User registered successfully");
        response.setData(UserDTO.from(user));

        return ResponseEntity.ok(response);
    }

    @PostMapping("login")
    public ResponseEntity<BaseApiResponseDTO<AuthUserResponse>> login(@Valid @RequestBody AuthenticateUserDTO authenticateUserDTO) throws TranslatedException {
        var authResponse = userService.authenticateUser(authenticateUserDTO);

        var response = new BaseApiResponseDTO<AuthUserResponse>();
        response.setMessage("Authentication successful");
        response.setData(authResponse);

        return ResponseEntity.ok(response);
    }

}
