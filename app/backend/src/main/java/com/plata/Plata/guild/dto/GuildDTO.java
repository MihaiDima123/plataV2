package com.plata.Plata.guild.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.plata.Plata.guild.entity.Guild;
import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuildDTO {
    private int id;
    private String name;
    private String description;

    public static GuildDTO from(@Nonnull Guild guild) {
        Objects.requireNonNull(guild, "Guild cannot be null");

        return GuildDTO.builder()
                .id(guild.getId())
                .name(guild.getName())
                .description(guild.getDescription())
                .build();
    }
}
