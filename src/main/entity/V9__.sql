ALTER TABLE users
    ADD deleted BOOLEAN;

ALTER TABLE users
    ADD is_admin BOOLEAN;

ALTER TABLE users
    ADD CONSTRAINT uc_users_public UNIQUE (public_id);

ALTER TABLE users
    DROP
        COLUMN bio;

ALTER TABLE users
    DROP
        COLUMN display_name;


ALTER TABLE users
    DROP
        COLUMN is_verified;

ALTER TABLE users
    DROP
        COLUMN status;
ALTER TABLE users
    ADD deleted BOOLEAN;

ALTER TABLE users
    ADD is_admin BOOLEAN;

ALTER TABLE users
    ADD CONSTRAINT uc_users_public UNIQUE (public_id);

ALTER TABLE users
    DROP COLUMN bio;

ALTER TABLE users
    DROP COLUMN display_name;

ALTER TABLE users
    DROP COLUMN is_verified;

ALTER TABLE users
    DROP COLUMN status;
ALTER TABLE users
    ADD avatar VARCHAR(255);

ALTER TABLE users
    ADD banner VARCHAR(255);

ALTER TABLE users
    ADD deleted BOOLEAN;

ALTER TABLE users
    ADD is_admin BOOLEAN;

ALTER TABLE users
    ADD is_banned BOOLEAN;

ALTER TABLE users
    ADD CONSTRAINT uc_users_public UNIQUE (public_id);

ALTER TABLE users
    ALTER COLUMN bio TYPE VARCHAR(255) USING (bio::VARCHAR(255));

ALTER TABLE users
    ALTER COLUMN display_name TYPE VARCHAR(255) USING (display_name::VARCHAR(255));

ALTER TABLE users
    ALTER COLUMN is_verified DROP NOT NULL;

ALTER TABLE users
    ALTER COLUMN status TYPE VARCHAR(255) USING (status::VARCHAR(255));
ALTER TABLE users
    ADD avatar VARCHAR(255);

ALTER TABLE users
    ADD banner VARCHAR(255);

ALTER TABLE users
    ADD deleted BOOLEAN;

ALTER TABLE users
    ADD is_admin BOOLEAN;

ALTER TABLE users
    ADD is_banned BOOLEAN;

ALTER TABLE users
    ADD CONSTRAINT uc_users_public UNIQUE (public_id);


ALTER TABLE users
    ALTER COLUMN display_name TYPE VARCHAR(255) USING (display_name::VARCHAR(255));

ALTER TABLE users
    ALTER COLUMN is_verified DROP NOT NULL;

ALTER TABLE users
    ALTER COLUMN status TYPE VARCHAR(255) USING (status::VARCHAR(255));
ALTER TABLE users
    ADD avatar VARCHAR(255);

ALTER TABLE users
    ADD banner VARCHAR(255);

ALTER TABLE users
    ADD deleted BOOLEAN;

ALTER TABLE users
    ADD is_admin BOOLEAN;

ALTER TABLE users
    ADD is_banned BOOLEAN;

ALTER TABLE users
    ADD CONSTRAINT uc_users_public UNIQUE (public_id);


ALTER TABLE users
    ALTER COLUMN display_name TYPE VARCHAR(255) USING (display_name::VARCHAR(255));

ALTER TABLE users
    ALTER COLUMN is_verified DROP NOT NULL;

ALTER TABLE users
    ALTER COLUMN status TYPE VARCHAR(255) USING (status::VARCHAR(255));
ALTER TABLE users
    ADD avatar VARCHAR(255);

ALTER TABLE users
    ADD banner VARCHAR(255);

ALTER TABLE users
    ADD deleted BOOLEAN;

ALTER TABLE users
    ADD is_admin BOOLEAN;

ALTER TABLE users
    ADD is_banned BOOLEAN;

ALTER TABLE users
    ADD CONSTRAINT uc_users_public UNIQUE (public_id);

ALTER TABLE users
    ALTER COLUMN display_name TYPE VARCHAR(255) USING (display_name::VARCHAR(255));

ALTER TABLE users
    ALTER COLUMN is_verified DROP NOT NULL;

ALTER TABLE users
    ALTER COLUMN status TYPE VARCHAR(255) USING (status::VARCHAR(255));
ALTER TABLE users
    ADD avatar VARCHAR(255);

ALTER TABLE users
    ADD banner VARCHAR(255);

ALTER TABLE users
    ADD bio VARCHAR(255);

ALTER TABLE users
    ADD display_name VARCHAR(255);

ALTER TABLE users
    ADD is_banned BOOLEAN;

ALTER TABLE users
    ADD is_verified BOOLEAN;

ALTER TABLE users
    ADD status VARCHAR(255);
ALTER TABLE users
    ADD avatar VARCHAR(255);

ALTER TABLE users
    ADD banner VARCHAR(255);

ALTER TABLE users
    ADD bio VARCHAR(255);

ALTER TABLE users
    ADD display_name VARCHAR(255);

ALTER TABLE users
    ADD is_banned BOOLEAN;

ALTER TABLE users
    ADD is_verified BOOLEAN;

ALTER TABLE users
    ADD status VARCHAR(255);
ALTER TABLE users
    ADD avatar VARCHAR(255);

ALTER TABLE users
    ADD banner VARCHAR(255);

ALTER TABLE users
    ADD bio VARCHAR(255);

ALTER TABLE users
    ADD display_name VARCHAR(255);

ALTER TABLE users
    ADD is_banned BOOLEAN;

ALTER TABLE users
    ADD is_verified BOOLEAN;

ALTER TABLE users
    ADD status VARCHAR(255);
ALTER TABLE users
    ADD avatar VARCHAR(255);

ALTER TABLE users
    ADD banner VARCHAR(255);

ALTER TABLE users
    ADD bio VARCHAR(255);

ALTER TABLE users
    ADD display_name VARCHAR(255);

ALTER TABLE users
    ADD is_banned BOOLEAN;

ALTER TABLE users
    ADD is_verified BOOLEAN;

ALTER TABLE users
    ADD status VARCHAR(255);
ALTER TABLE users
    ADD bio VARCHAR(255);

ALTER TABLE users
    ADD display_name VARCHAR(255);

ALTER TABLE users
    ADD is_banned BOOLEAN;

ALTER TABLE users
    ADD is_verified BOOLEAN;

ALTER TABLE users
    ADD status VARCHAR(255);
ALTER TABLE users
    ADD display_name VARCHAR(255);

ALTER TABLE users
    ADD is_banned BOOLEAN;

ALTER TABLE users
    ADD status VARCHAR(255);
ALTER TABLE users
    ADD display_name VARCHAR(255);

ALTER TABLE users
    ADD is_banned BOOLEAN;
ALTER TABLE users
    ADD display_name VARCHAR(255);
ALTER TABLE guilds
    DROP CONSTRAINT fk_guild_owner;

ALTER TABLE guilds
    ADD guild_id VARCHAR(255);

ALTER TABLE guilds
    ALTER COLUMN guild_id SET NOT NULL;

ALTER TABLE guilds
    ADD CONSTRAINT uc_guilds_guild UNIQUE (guild_id);

ALTER TABLE guilds
    ADD CONSTRAINT FK_GUILDS_ON_OWNER FOREIGN KEY (owner_id) REFERENCES members (id);

ALTER TABLE guilds
    ALTER COLUMN channel_count DROP NOT NULL;

ALTER TABLE guilds
    ALTER COLUMN deleted DROP NOT NULL;

ALTER TABLE guilds
    ALTER COLUMN invite_only DROP NOT NULL;

ALTER TABLE guilds
    ALTER COLUMN is_public DROP NOT NULL;

ALTER TABLE guilds
    ALTER COLUMN member_count DROP NOT NULL;
ALTER TABLE guilds
    DROP CONSTRAINT fk_guild_owner;

ALTER TABLE guilds
    ADD guild_id VARCHAR(255);

ALTER TABLE guilds
    ALTER COLUMN guild_id SET NOT NULL;

ALTER TABLE guilds
    ADD CONSTRAINT uc_guilds_guild UNIQUE (guild_id);

ALTER TABLE guilds
    ADD CONSTRAINT FK_GUILDS_ON_OWNER FOREIGN KEY (owner_id) REFERENCES members (id);

ALTER TABLE guilds
    ALTER COLUMN channel_count DROP NOT NULL;

ALTER TABLE guilds
    ALTER COLUMN deleted DROP NOT NULL;

ALTER TABLE guilds
    ALTER COLUMN invite_only DROP NOT NULL;

ALTER TABLE guilds
    ALTER COLUMN is_public DROP NOT NULL;

ALTER TABLE guilds
    ALTER COLUMN member_count DROP NOT NULL;
ALTER TABLE guilds
    DROP CONSTRAINT fk_guild_owner;

ALTER TABLE guilds
    ADD guild_id VARCHAR(255);

ALTER TABLE guilds
    ALTER COLUMN guild_id SET NOT NULL;

ALTER TABLE guilds
    ADD CONSTRAINT uc_guilds_guild UNIQUE (guild_id);

ALTER TABLE guilds
    ADD CONSTRAINT FK_GUILDS_ON_OWNER FOREIGN KEY (owner_id) REFERENCES members (id);

ALTER TABLE guilds
    ALTER COLUMN channel_count DROP NOT NULL;

ALTER TABLE guilds
    ALTER COLUMN deleted DROP NOT NULL;

ALTER TABLE guilds
    ALTER COLUMN invite_only DROP NOT NULL;

ALTER TABLE guilds
    ALTER COLUMN is_public DROP NOT NULL;

ALTER TABLE guilds
    ALTER COLUMN member_count DROP NOT NULL;
ALTER TABLE guilds
    DROP CONSTRAINT fk_guild_owner;

ALTER TABLE guilds
    ADD guild_id VARCHAR(255);

ALTER TABLE guilds
    ALTER COLUMN guild_id SET NOT NULL;

ALTER TABLE guilds
    ADD CONSTRAINT uc_guilds_guild UNIQUE (guild_id);

ALTER TABLE guilds
    ADD CONSTRAINT FK_GUILDS_ON_OWNER FOREIGN KEY (owner_id) REFERENCES members (id);

ALTER TABLE guilds
    ALTER COLUMN channel_count DROP NOT NULL;

ALTER TABLE guilds
    ALTER COLUMN deleted DROP NOT NULL;

ALTER TABLE guilds
    ALTER COLUMN invite_only DROP NOT NULL;

ALTER TABLE guilds
    ALTER COLUMN is_public DROP NOT NULL;

ALTER TABLE guilds
    ALTER COLUMN member_count DROP NOT NULL;