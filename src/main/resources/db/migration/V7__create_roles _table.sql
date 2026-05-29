CREATE TABLE roles
(

    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,

    permissions BIGINT       NOT NULL,

    position    INT          NOT NULL,

    color       VARCHAR(255),

    mentionable BOOLEAN      NOT NULL DEFAULT TRUE,

    hoisted     BOOLEAN      NOT NULL DEFAULT FALSE,

    guild_id    BIGINT       NOT NULL,

    CONSTRAINT uq_role_guild_name
        UNIQUE (guild_id, name),

    CONSTRAINT fk_role_guild
        FOREIGN KEY (guild_id)
            REFERENCES guilds (id)
);