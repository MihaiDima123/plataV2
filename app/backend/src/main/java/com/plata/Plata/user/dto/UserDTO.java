package com.plata.Plata.user.dto;

import com.plata.Plata.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Set;

@Getter
@Setter
@Builder
public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private String locale;
    private Set<String> permissions;

    public static UserDTO from(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .locale(LocaleContextHolder.getLocale().getLanguage())
                .build();
    }
}
