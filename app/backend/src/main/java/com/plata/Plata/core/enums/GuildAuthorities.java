package com.plata.Plata.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GuildAuthorities {
    CREATE_GUILD("guild:create-guild");
    private final String authority;
}
