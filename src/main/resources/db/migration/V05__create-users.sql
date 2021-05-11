CREATE TABLE users
(
    user_id   BIGINT       NOT NULL,
    user_name VARCHAR(30)  NULL,
    password  VARCHAR(255) NULL,
    email     varchar(30)  NULL,
    PRIMARY KEY (user_id)
);

INSERT INTO users
VALUES (1, 'admin', '$2y$12$1x466b6HgJPXSv9sQKt5V.zjqdQnZwL4lKYTrJ0ORLoWFiizVpHmO', 'admin@ms3-inc.com');
INSERT INTO users
VALUES (2, 'user', '$2y$12$gik6iEQdF2.h7pl4g/k1qONPAsWsxd2JR3DMPJfPfpdw8fXVQpXfi', 'user@ms3-inc.com');