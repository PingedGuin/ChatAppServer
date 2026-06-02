package com.app.register.security.user;

import lombok.Data;

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

    private String password;
}