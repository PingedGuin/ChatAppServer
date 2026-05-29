package com.app.member.service;

import com.app.member.dto.MemberDto;
import com.app.member.entity.MemberEntity;
import com.app.member.repository.MemberRepository;
import com.app.role.dto.RoleDto;
import com.app.user.data.dto.MemberPermissionDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberDto getUserPermissions(Long id, Long guildId) {
        return null;
    }

    public MemberPermissionDto getMemberContext(Long userId, Long guildId) {

        MemberEntity entity = memberRepository
                .findByUserInfo_Id(userId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        List<RoleDto> roles = entity.getRoles()
                .stream()
                .map(role -> new RoleDto(
                        role.getId(),
                        role.getName(),
                        role.getGuild().getId(),
                        role.getPermissions()
                ))
                .toList();

        Set<Long> roleIds = roles.stream()
                .map(RoleDto::getRoleId)
                .collect(Collectors.toSet());


        MemberPermissionDto dto = new MemberPermissionDto();
        dto.setUserId(entity.getUserInfo().getId());
        dto.setGuildId(guildId);
        dto.setRoles(roles);
        dto.setRoleIds(roleIds);

        return dto;
    }

}
