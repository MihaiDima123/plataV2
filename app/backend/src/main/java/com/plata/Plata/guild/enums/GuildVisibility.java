package com.plata.Plata.guild.enums;

import jakarta.annotation.Nonnull;

public enum GuildVisibility {
    PUBLIC,
    PRIVATE,
    SECRET;

    public static GuildVisibility fromValue(@Nonnull String value) {
        return GuildVisibility.valueOf(value.toUpperCase());
    }
}
