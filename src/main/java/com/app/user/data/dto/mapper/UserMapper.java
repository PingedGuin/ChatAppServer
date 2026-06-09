package com.app.user.data.dto.mapper;

import com.app.user.data.dto.UserDto;
import com.app.user.data.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(UserEntity entity) {
        return new UserDto(entity.getId(), entity.getUsername(), entity.getDisplayName(),
                entity.getEmail(), entity.getAvatar(), entity.getBanner(), entity.getStatus(),
                entity.isVerified(), entity.getBio());
    }

    public UserEntity toEntity(UserDto user) {
        UserEntity entity = new UserEntity();

        entity.setUsername(user.getUsername());
        entity.setDisplayName(user.getDisplayName());
        entity.setBio(user.getBio());
        entity.setEmail(user.getEmail());
        entity.setAvatar(user.getAvatar());
        entity.setStatus(user.getStatus());
        return entity;
    }
}
