package com.app.user.data.dto;

import com.app.user.data.entity.UserInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

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

    public UserDto(UserInfoEntity userInfoEntity) {
        this.id = userInfoEntity.getId();
        this.username = userInfoEntity.getUsername();
        this.displayName = userInfoEntity.getDisplayName();
        this.email = userInfoEntity.getEmail();
        this.avatar = userInfoEntity.getAvatar();
        this.banner = userInfoEntity.getBanner();
        this.status = userInfoEntity.getStatus();
        this.verified = userInfoEntity.isVerified();
    }
}