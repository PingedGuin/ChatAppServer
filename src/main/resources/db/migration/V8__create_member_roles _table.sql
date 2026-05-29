CREATE TABLE member_roles (
    member_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    PRIMARY KEY (member_id, role_id),

    CONSTRAINT fk_member_roles_member
        FOREIGN KEY (member_id)
        REFERENCES members(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_member_roles_role
        FOREIGN KEY (role_id)
        REFERENCES roles(id)
        ON DELETE CASCADE
);