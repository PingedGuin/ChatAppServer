package com.app.role.service;

import com.app.guild.service.GuildService;
import com.app.role.repository.RoleRepository;
import org.springframework.stereotype.Service;


@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final GuildService guildService;

    public RoleService(RoleRepository roleRepository, GuildService guildService) {
        this.roleRepository = roleRepository;
        this.guildService = guildService;
    }

    public void createDefaultRole(Long guildId) {

    }

//    Create Role
//    Update Role
//    Delete Role
//    Get Role
//    Fetching
//    Get Roles by Guild
//    Get Role by ID
//    Permissions
//    Add Permission
//    Remove Permission
//    Set Permissions
//    Clear Permissions
//    Validation
//    Check Duplicate Name
//    Validate Creation Rules
//    Protect System Roles
//            Mapping
//    entity ↔ DTO
//    Not in RoleService
//    Member Permissions
//    Channel Logic
//    Guild Context
//    System Caching

}

