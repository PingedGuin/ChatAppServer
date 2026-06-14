package com.app.guild.controller;

import com.app.guild.data.dto.guild.GuildCreate;
import com.app.guild.data.dto.guild.GuildCreateRequest;
import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.service.GuildService;
import com.app.member.service.MemberService;
import com.app.user.data.dto.UserDto;
import com.app.user.data.dto.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GuildController {
    private final MemberService memberService;
    private final GuildService guildService;

    public GuildController(MemberService memberService, GuildService guildService) {
        this.memberService = memberService;
        this.guildService = guildService;
    }

    @GetMapping("/api/guild/guilds")
    public ResponseEntity<?> getUserGuilds(Authentication auth, @AuthenticationPrincipal UserPrincipal user) {
        if (user == null) return ResponseEntity.status(401).body("Not logged in");

        Long userId = user.getId();
        List<GuildInfoDto> guildInfoDtoList = memberService.getUserGuilds(userId);

        return ResponseEntity.ok(guildInfoDtoList);
    }

    @PostMapping("/api/guild/create")
    public ResponseEntity<GuildInfoDto> createGuild(
            @AuthenticationPrincipal UserPrincipal user,
            @RequestBody GuildCreateRequest request) {

        GuildCreate dto = new GuildCreate(
                user.getId(),
                request.getName()
        );

        GuildInfoDto created = guildService.createGuild(dto);

        return ResponseEntity.ok(created);
    }

    @DeleteMapping("/api/guild/delete/{guildId}")
    public ResponseEntity<?> deleteGuild(@PathVariable String guildId, @AuthenticationPrincipal UserPrincipal user) {

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> editGuildSettings(@AuthenticationPrincipal UserDto user) {
        return ResponseEntity.ok().build();
    }
}
