package com.plata.Plata.guild.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.plata.Plata.guild.enums.GuildVisibility;
import com.plata.Plata.guild.validation.ValidGuildVisibility;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateGuildDTO {
    @Size(min = 3, max = 50)
    private String name;
    private String description;

    @ValidGuildVisibility
    private String visibility = GuildVisibility.PRIVATE.toString();
}
