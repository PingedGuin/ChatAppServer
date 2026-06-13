package com.app.member.service;

import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.data.entity.GuildEntity;
import com.app.member.dto.MemberDto;
import com.app.member.entity.MemberEntity;
import com.app.member.repository.MemberRepository;
import com.app.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final UserService userService;
    public MemberService(MemberRepository memberRepository, UserService userService) {
        this.memberRepository = memberRepository;
        this.userService = userService;
    }

    public MemberDto getUserPermissions(Long id, Long guildId) {
        return null;
    }

//    public MemberPermissionDto getMemberContext(Long userId, Long guildId) {
//
//        List<MemberEntity> entity = memberRepository
//                .findByUserInfo_Id(userId);
//
//        List<RoleDto> roles = entity.getRoles()
//                .stream()
//                .map(role -> new RoleDto(
//                        role.getId(),  //todo fix this
//                        role.getName(),
//                        role.getGuild().getId(),
//                        role.getPermissions()
//                ))
//                .toList();
//
//        Set<Long> roleIds = roles.stream()
//                .map(RoleDto::getRoleId)
//                .collect(Collectors.toSet());
//
//
//        MemberPermissionDto dto = new MemberPermissionDto();
//        dto.setUserId(entity.getUserInfo().getId());
//        dto.setGuildId(guildId);
//        dto.setRoles(roles);
//        dto.setRoleIds(roleIds);
//
//        return dto;
//    }

    public List<GuildInfoDto> getUserGuilds(Long userId) {
        List<GuildEntity> guilds = memberRepository.findGuildsByUserId(userId);
        return guilds.stream()
                .map(g -> new GuildInfoDto(
                        g.getId(),
                        g.getName(),
                        g.getDescription(),
                        g.getGuildIcon(),
                        g.getGuildBanner(),
                        g.getOwner().getId(),
                        g.getMemberCount(),
                        g.getChannelCount()
                ))
                .toList();
    }

    public void save(MemberEntity memberEntity) {
        memberRepository.save(memberEntity);
    }
}
