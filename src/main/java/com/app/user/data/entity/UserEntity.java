package com.app.user.data.entity;

import com.app.register.dtos.register.RegisterRequest;
import com.app.user.data.dto.ApplicationRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id", nullable = false, unique = true, updatable = false)
    private UUID publicId;

    @Column(nullable = false, length = 32, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @Column(name = "is_banned")
    private boolean isBanned;

    @Column(name = "banner")
    private String banner;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "bio")
    private String bio;
    @Column(name = "display_name")
    private String displayName;

    @Column(name = "is_verified")
    private boolean isVerified;

    @Enumerated(EnumType.STRING)
    private ApplicationRole role;

    @Column(name = "status")
    private String status;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (publicId == null) {
            publicId = UUID.randomUUID();
        }
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public static UserEntity from(RegisterRequest request, String encodedPassword) {
        return UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(encodedPassword)
                .build();
    }
}