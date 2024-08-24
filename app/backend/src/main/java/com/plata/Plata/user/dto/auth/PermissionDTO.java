package com.plata.Plata.user.dto.auth;

import com.plata.Plata.user.entity.Permissions;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PermissionDTO implements Serializable {
    private String name;

    public static PermissionDTO from(Permissions permissions) {
        PermissionDTO dto = new PermissionDTO();
        dto.setName(permissions.getName());

        return dto;
    }
}
