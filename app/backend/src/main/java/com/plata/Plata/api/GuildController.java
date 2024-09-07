package com.plata.Plata.api;

import com.plata.Plata.api.base.BaseApiController;
import com.plata.Plata.core.configuration.provider.ApiBuilderProvider;
import com.plata.Plata.core.dto.BaseApiResponseDTO;
import com.plata.Plata.core.exception.TranslatedException;
import com.plata.Plata.guild.dto.CreateGuildDTO;
import com.plata.Plata.guild.service.GuildService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/guild")
public class GuildController extends BaseApiController {
    private final GuildService guildService;

    public GuildController(GuildService guildService,
                           ApiBuilderProvider apiBuilderProvider) {
        super(apiBuilderProvider);
        this.guildService = guildService;
    }

    @PostMapping
    public ResponseEntity<BaseApiResponseDTO<Integer>> createGuild(@RequestBody @Valid CreateGuildDTO createGuildDTO) throws TranslatedException {
        var createdGuild = guildService.createGuild(createGuildDTO);

        return ResponseEntity.ok()
                .body(apiBuilderProvider.<Integer>builder()
                        .data(createdGuild.getId())
                        .build());
    }
}
