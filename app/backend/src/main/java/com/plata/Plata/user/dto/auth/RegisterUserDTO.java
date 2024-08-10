package com.plata.Plata.user.dto.auth;

import com.plata.Plata.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
public class RegisterUserDTO {
    @Size(min = 3, max = 30, message = "Username should not be less than 3 characters")
    private String username;

    @Size(min = 8, max = 50, message = "Password should be at least 8 characters")
    private String password;

    @Email(message = "Not a valid email")
    private String email;

    public User toUserEntity(PasswordEncoder passwordEncoder) {
        var user = new User();

        user.setPassword(passwordEncoder.encode(getPassword()));
        user.setUsername(getUsername());
        user.setEmail(getEmail());

        return user;
    }
}
