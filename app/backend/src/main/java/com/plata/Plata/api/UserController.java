package com.plata.Plata.api;

import com.plata.Plata.api.base.BaseApiController;
import com.plata.Plata.core.configuration.provider.ApiBuilderProvider;
import com.plata.Plata.core.dto.BaseApiResponseDTO;
import com.plata.Plata.core.exception.ForbiddenException;
import com.plata.Plata.user.dto.UserDTO;
import com.plata.Plata.user.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController extends BaseApiController {
    private final UserService userService;

    public UserController(ApiBuilderProvider translatedMessageBuilderProvider,
                          UserService userService) {
        super(translatedMessageBuilderProvider);
        this.userService = userService;
    }

    @GetMapping("self-user")
    public ResponseEntity<BaseApiResponseDTO<UserDTO>> getSelfUser() throws ForbiddenException {
        return ResponseEntity.ok().body(
                apiBuilderProvider.<UserDTO>builder()
                        .data(userService.getSelfUserDto())
                        .build()
        );
    }
}
