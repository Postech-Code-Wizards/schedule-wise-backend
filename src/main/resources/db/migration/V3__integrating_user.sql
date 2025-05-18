CREATE TYPE user_type AS ENUM ('DOCTOR', 'NURSE', 'PATIENT');

CREATE TABLE IF NOT EXISTS tb_user (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(254) NOT NULL,
    user_type user_type NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ DEFAULT now(),
    deleted_at TIMESTAMPTZ
);