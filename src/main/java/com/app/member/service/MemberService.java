package com.app.member.service;

import com.app.guild.data.dto.guild.GuildInfoDto;
import com.app.guild.data.entity.GuildEntity;
import com.app.guild.repository.GuildRepository;
import com.app.member.dto.MemberDto;
import com.app.member.entity.MemberEntity;
import com.app.member.repository.MemberRepository;
import com.app.user.data.entity.UserEntity;
import com.app.user.repository.UserInfoRepository;
import com.app.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final GuildRepository guildRepository;
    private final UserInfoRepository userInfoRepository;
    public MemberService(MemberRepository memberRepository, GuildRepository guildRepository, UserInfoRepository userInfoRepository) {
        this.memberRepository = memberRepository;
        this.guildRepository = guildRepository;
        this.userInfoRepository = userInfoRepository;
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

    @Transactional
    public void createOwnerMember(Long ownerId, Long guildId) {

        UserEntity user = userInfoRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        GuildEntity guild = guildRepository.findById(guildId)
                .orElseThrow(() -> new RuntimeException("Guild not found"));

        MemberEntity member = MemberEntity.builder()
                .userInfo(user)
                .guild(guild)
                .username(user.getUsername())
                .nickname(user.getUsername())
                .owner(true)
                .muted(false)
                .banned(false)
                .build();

         memberRepository.save(member);
    }

    public List<GuildInfoDto> getMemberGuilds(Long userId) {
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
}
