package com.app.guild.controller;

import com.app.guild.data.dto.guild.GuildCreate;
import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.service.GuildService;
import com.app.register.security.user.UserPrincipal;
import com.app.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
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

    @GetMapping("/api/guild/me/guilds")
    public ResponseEntity<?> getUserGuilds(Authentication auth) {
        Long userId = Long.valueOf(auth.getName());
        List<GuildInfoDto> guildInfoDtoList = userService.getUserGuilds(userId);

        return ResponseEntity.ok(guildInfoDtoList);
    }
    @PostMapping("/api/guild/create")
    public ResponseEntity<?> createGuild(Authentication auth, @AuthenticationPrincipal UserPrincipal user, @RequestBody  String guildName) { // todo change it make a class for create guild

        Long ownerId = user.getId();
        guildService.createGuild(new GuildCreate(ownerId, guildName));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/guild/delete/{guildId}")
    public ResponseEntity<?> deleteGuild(Authentication auth, @PathVariable String guildId) {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> editGuildSettings(Authentication auth) {
        return ResponseEntity.ok().build();
    }
}
