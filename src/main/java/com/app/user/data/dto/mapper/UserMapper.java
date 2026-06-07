package com.app.user.data.dto.mapper;

import com.app.user.data.dto.UserDto;
import com.app.user.data.entity.UserInfoEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(UserInfoEntity entity) {
        return new UserDto(entity.getId(), entity.getUsername(), entity.getDisplayName(),
                entity.getEmail(), entity.getAvatar(), entity.getBanner(), entity.getStatus(),
                entity.isVerified(), entity.getRole(), entity.getBio());
    }

    public UserInfoEntity toEntity(UserDto user) {
        UserInfoEntity entity = new UserInfoEntity();

        entity.setUsername(user.getUsername());
        entity.setDisplayName(user.getDisplayName());
        entity.setBio(user.getBio());
        entity.setRole(user.getRole());
        entity.setEmail(user.getEmail());
        entity.setAvatar(user.getAvatar());
        entity.setStatus(user.getStatus());
        return entity;
    }
}
