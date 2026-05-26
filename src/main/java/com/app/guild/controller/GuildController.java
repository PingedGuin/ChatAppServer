package com.app.guild.controller;

import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.service.GuildService;
import org.osgi.annotation.bundle.Header;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GuildController {
    private final GuildService guildService;

    public GuildController(GuildService guildService) {
        this.guildService = guildService;
    }

    @GetMapping("/api/guild/me/guilds")
    public ResponseEntity<?> getUserGuilds(Authentication auth) {
        Long userId = Long.valueOf(auth.getName());
        List<GuildInfoDto> guildInfoDtoList = guildService.getUserGuilds(userId);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> createGuild(Authentication auth) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/guild/delete/{guildId}")
    public ResponseEntity<?> deleteGuild(Authentication auth) {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> editGuildSettings(Authentication auth) {
        return ResponseEntity.ok().build();
    }
}
