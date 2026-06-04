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

    public UserPrincipal(UserInfoEntity userInfoEntity) {
        this.id = userInfoEntity.getId();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_USER")

        );
    }
}