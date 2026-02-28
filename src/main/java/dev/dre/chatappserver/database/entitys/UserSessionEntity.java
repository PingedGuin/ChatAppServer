package dev.dre.chatappserver.database.entitys;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user_sessions")
public class UserSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, unique = true)
    private String sessionId;

    @Column(nullable = false)
    private boolean active;

    public UserSessionEntity(String userId, String sessionId, boolean active) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.active = active;
    }

}