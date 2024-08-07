package com.plata.Plata.user.services;

import com.plata.Plata.core.exception.BadRequestException;
import com.plata.Plata.core.exception.NotFoundException;
import com.plata.Plata.core.exception.TranslatedException;
import com.plata.Plata.user.dto.auth.RegisterUserDTO;
import com.plata.Plata.user.entity.User;
import com.plata.Plata.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegisterUserDTO registerUserDTO) throws TranslatedException {
        if (userRepository.countByEmail(registerUserDTO.getEmail()) > 0
                || userRepository.countByUsername(registerUserDTO.getUsername()) > 0) {
            throw new BadRequestException("User already exists");
        }

        final var user = registerUserDTO.toUserEntity(passwordEncoder);

        return userRepository.save(user);
    }

    public User getByUsername(String username) throws NotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("errors.user"));
    }
}
