CREATE TABLE guilds
(
    id BIGSERIAL PRIMARY KEY,
    guild_name    VARCHAR(100) NOT NULL,
    description   VARCHAR(500),

    guild_icon    VARCHAR(255),
    guild_banner  VARCHAR(255),

    owner_id      BIGINT       NOT NULL,

    is_public     BOOLEAN      NOT NULL DEFAULT TRUE,
    invite_only   BOOLEAN      NOT NULL DEFAULT FALSE,

    member_count  INT          NOT NULL DEFAULT 0,
    channel_count INT          NOT NULL DEFAULT 0,

    deleted       BOOLEAN      NOT NULL DEFAULT FALSE,

    created_at    TIMESTAMP     NOT NULL,
    updated_at    TIMESTAMP,

    CONSTRAINT fk_guild_owner
        FOREIGN KEY (owner_id)
            REFERENCES users(id)
);

CREATE INDEX idx_guild_name
    ON guilds (guild_name);

CREATE INDEX idx_owner_id
    ON guilds (owner_id);