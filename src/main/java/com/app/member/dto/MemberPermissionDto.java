package com.app.member.dto;

import com.app.role.dto.RoleDto;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class MemberPermissionDto {
    private Long userId;
    private Long guildId;
    private List<RoleDto> roles;
    private Set<Long> roleIds;

}
