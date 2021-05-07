CREATE TABLE users
(
    user_id   BIGINT       NOT NULL,
    user_name VARCHAR(30)  NULL,
    password  VARCHAR(255) NULL,
    email     varchar(30)  NULL,
    PRIMARY KEY (user_id)
);

INSERT INTO users
VALUES (1, 'admin', 'admin', 'admin@ms3-inc.com');
INSERT INTO users
VALUES (2, 'user', 'user', 'user@ms3-inc.com');