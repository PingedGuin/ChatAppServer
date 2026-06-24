ALTER TABLE workflow_definitions
    ADD version INTEGER;

ALTER TABLE workflow_definitions
    ALTER COLUMN version SET NOT NULL;

ALTER TABLE workflow_definitions
ALTER
COLUMN name TYPE VARCHAR(255) USING (name::VARCHAR(255));