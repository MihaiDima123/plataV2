package com.plata.Plata.user.services;

import com.plata.Plata.core.enums.AuthenticationType;
import com.plata.Plata.core.exception.BadRequestException;
import com.plata.Plata.core.exception.ForbiddenException;
import com.plata.Plata.core.exception.NotFoundException;
import com.plata.Plata.core.exception.TranslatedException;
import com.plata.Plata.core.jwt.JwtUtils;
import com.plata.Plata.user.dto.auth.AuthUserResponse;
import com.plata.Plata.user.dto.auth.AuthenticateUserDTO;
import com.plata.Plata.user.dto.auth.RegisterUserDTO;
import com.plata.Plata.user.entity.User;
import com.plata.Plata.user.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    /**
     * Auth Stuff
     */
    public User registerUser(RegisterUserDTO registerUserDTO) throws TranslatedException {
        if (userRepository.countByEmail(registerUserDTO.getEmail()) > 0
                || userRepository.countByUsername(registerUserDTO.getUsername()) > 0) {
            throw new BadRequestException("User already exists");
        }

        final var user = registerUserDTO.toUserEntity(passwordEncoder);

        return userRepository.save(user);
    }

    public AuthUserResponse authenticateUser(AuthenticateUserDTO authenticateUserDTO) throws TranslatedException {
        var user = userRepository.findByUsername(authenticateUserDTO.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!passwordEncoder.matches(authenticateUserDTO.getPassword(), user.getPassword())) {
            throw new ForbiddenException();
        }

        var authentication = new UsernamePasswordAuthenticationToken(
            user.getUsername(),
            user.getPassword(),
            Collections.emptyList()
        );

        var token = jwtUtils.generateToken(authentication);

        return AuthUserResponse.builder()
                .token(token)
                .type(AuthenticationType.COOKIE)
                .build();
    }
}
