package com.app.guild.controller;

import com.app.guild.data.dto.guild.GuildCreate;
import com.app.guild.data.dto.guild.GuildCreateRequest;
import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.service.GuildService;
import com.app.user.data.dto.UserDto;
import com.app.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GuildController {
    private final UserService userService;
    private final GuildService guildService;

    public GuildController(UserService userService, GuildService guildService) {
        this.userService = userService;
        this.guildService = guildService;
    }

    @GetMapping("/api/guild/guilds")
    public ResponseEntity<?> getUserGuilds(Authentication auth) {
        UserDto principal =
                (UserDto) auth.getPrincipal();

        if (principal == null) return ResponseEntity.status(401).body("Not logged in");

        Long userId = principal.getId();
        List<GuildInfoDto> guildInfoDtoList = userService.getUserGuilds(userId);

        return ResponseEntity.ok(guildInfoDtoList);
    }
    @PostMapping("/api/guild/create")
    public ResponseEntity<GuildInfoDto> createGuild(
            @AuthenticationPrincipal UserDto user,
            @RequestBody GuildCreateRequest request) {

        GuildCreate dto = new GuildCreate(
                user.getId(),
                request.getName()
        );

        GuildInfoDto created = guildService.createGuild(dto);

        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/api/guild/delete/{guildId}")
    public ResponseEntity<?> deleteGuild(@PathVariable String guildId) {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> editGuildSettings(@AuthenticationPrincipal UserDto user) {
        return ResponseEntity.ok().build();
    }
}
