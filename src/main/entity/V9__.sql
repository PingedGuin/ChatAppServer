ALTER TABLE users
    ADD deleted BOOLEAN;

ALTER TABLE users
    ADD is_admin BOOLEAN;

ALTER TABLE users
    ADD CONSTRAINT uc_users_public UNIQUE (public_id);

ALTER TABLE users
DROP
COLUMN avatar_url;

ALTER TABLE users
DROP
COLUMN banner_url;

ALTER TABLE users
DROP
COLUMN bio;

ALTER TABLE users
DROP
COLUMN display_name;

ALTER TABLE users
DROP
COLUMN is_bot;

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
    DROP COLUMN avatar_url;

ALTER TABLE users
    DROP COLUMN banner_url;

ALTER TABLE users
    DROP COLUMN bio;

ALTER TABLE users
    DROP COLUMN display_name;

ALTER TABLE users
    DROP COLUMN is_bot;

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
    DROP COLUMN avatar_url;

ALTER TABLE users
    DROP COLUMN banner_url;

ALTER TABLE users
    DROP COLUMN is_bot;

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
    DROP COLUMN avatar_url;

ALTER TABLE users
    DROP COLUMN banner_url;

ALTER TABLE users
    DROP COLUMN is_bot;


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
    DROP COLUMN avatar_url;

ALTER TABLE users
    DROP COLUMN banner_url;

ALTER TABLE users
    DROP COLUMN is_bot;


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
    DROP COLUMN avatar_url;

ALTER TABLE users
    DROP COLUMN banner_url;

ALTER TABLE users
    DROP COLUMN is_bot;


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