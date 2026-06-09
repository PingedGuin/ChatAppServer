package com.app.user.data.dto;

import com.app.user.data.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String displayName;
    private String email;
    private String avatar;
    private String banner;
    private String status;
    private Boolean verified;
    private String bio;

    public UserDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.displayName = userEntity.getDisplayName();
        this.email = userEntity.getEmail();
        this.avatar = userEntity.getAvatar();
        this.banner = userEntity.getBanner();
        this.status = userEntity.getStatus();
        this.verified = userEntity.isVerified();
    }
}