CREATE TABLE members
(
    id BIGSERIAL PRIMARY KEY,
    username       VARCHAR(100),
    nickname       VARCHAR(100),

    avatar         VARCHAR(255),

    owner          BOOLEAN NOT NULL DEFAULT FALSE,
    muted          BOOLEAN NOT NULL DEFAULT FALSE,
    banned         BOOLEAN NOT NULL DEFAULT FALSE,

    joined_at      TIMESTAMP,
    last_active_at TIMESTAMP,

    user_id        BIGINT  NOT NULL,
    guild_id       BIGINT  NOT NULL,

    CONSTRAINT uq_member_guild_user
        UNIQUE (guild_id, user_id),

    CONSTRAINT fk_member_user
        FOREIGN KEY (user_id)
            REFERENCES users (id),

    CONSTRAINT fk_member_guild
        FOREIGN KEY (guild_id)
            REFERENCES guilds (id)
);

CREATE INDEX idx_member_guild
    ON members (guild_id);

CREATE INDEX idx_member_user
    ON members (user_id);

CREATE INDEX idx_member_guild_user
    ON members (guild_id, user_id);

CREATE INDEX idx_member_guild_banned
    ON members (guild_id, banned);