package com.app.user.data.dto;

import com.app.user.data.entity.UserInfoEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Data
public class UserPrincipal {

    private Long id;
    private String username;
    private String displayName;
    private String email;
    private String avatar;
    private String banner;
    private String status;
    private Boolean verified;
    private List<String> roles;

    public UserPrincipal(UserInfoEntity userInfoEntity) {
        this.id = userInfoEntity.getId();
        this.username = userInfoEntity.getUsername();
        this.displayName = userInfoEntity.getDisplayName();
        this.email = userInfoEntity.getEmail();
        this.avatar = userInfoEntity.getAvatar();
        this.banner = userInfoEntity.getBanner();
        this.status = userInfoEntity.getStatus();
        this.verified = userInfoEntity.isVerified();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();
    }
}