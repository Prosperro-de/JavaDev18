CREATE SEQUENCE IF NOT EXISTS seq_users_id START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS seq_roles_id START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS roles (
    id BIGINT DEFAULT nextval('seq_roles_id'),
    name VARCHAR(50) UNIQUE NOT NULL,
    CONSTRAINT pk_roles_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT DEFAULT nextval('seq_users_id'),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT true,
    customer_id BIGINT,
    CONSTRAINT pk_users_id PRIMARY KEY (id),
    CONSTRAINT fk_users_customer_id FOREIGN KEY (customer_id) REFERENCES customers (id)
);

CREATE TABLE IF NOT EXISTS users_roles (
    user_id BIGINT,
    role_id BIGINT,
    CONSTRAINT pk_users_roles PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_users_roles_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_users_roles_role_id FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE INDEX users_roles_user_idx ON users_roles(user_id);
CREATE INDEX users_roles_role_idx ON users_roles(role_id);