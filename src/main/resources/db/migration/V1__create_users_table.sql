CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY,

    public_id UUID NOT NULL DEFAULT gen_random_uuid(),

    username        VARCHAR(32)  NOT NULL UNIQUE,
    display_name    VARCHAR(32),

    email           VARCHAR(255) NOT NULL UNIQUE,
    password_hash   VARCHAR(255) NOT NULL,

    avatar_url      VARCHAR(255),
    banner_url      VARCHAR(255),

    bio             VARCHAR(190),

    is_bot          BOOLEAN NOT NULL DEFAULT FALSE,
    is_verified     BOOLEAN NOT NULL DEFAULT FALSE,

    status          VARCHAR(20) DEFAULT 'OFFLINE',

    created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP
);

CREATE INDEX idx_users_public_id ON users(public_id);
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);