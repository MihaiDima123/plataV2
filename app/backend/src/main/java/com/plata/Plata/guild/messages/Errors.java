package com.plata.Plata.guild.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Errors {
    GUILD_ALREADY_EXISTS("exception.guild.already-exists");
    final String message;
}
