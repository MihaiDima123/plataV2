package com.plata.Plata.guild.service;

import com.plata.Plata.core.exception.BadRequestException;
import com.plata.Plata.core.exception.TranslatedException;
import com.plata.Plata.core.threadcontext.UserContext;
import com.plata.Plata.guild.dto.CreateGuildDTO;
import com.plata.Plata.guild.entity.Guild;
import com.plata.Plata.guild.enums.GuildVisibility;
import com.plata.Plata.guild.messages.Errors;
import com.plata.Plata.guild.repository.GuildRepository;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GuildService {
    private final GuildRepository guildRepository;

    public GuildService(GuildRepository guildRepository) {
        this.guildRepository = guildRepository;
    }

    @Transactional
    public Guild createGuild(@Nonnull CreateGuildDTO createGuildDTO) throws TranslatedException {
        if (guildRepository.getCountByName(createGuildDTO.getName()) > 0) {
            throw new BadRequestException(Errors.GUILD_ALREADY_EXISTS.getMessage());
        }

        var guild = new Guild();
        guild.setName(createGuildDTO.getName());
        guild.setDescription(createGuildDTO.getDescription());
        guild.setVisibility(GuildVisibility.fromValue(createGuildDTO.getVisibility()));
        guild.setOwner(UserContext.get());

        guildRepository.save(guild);

        return guild;
    }
}
