CREATE TABLE customers
(
    customer_id  BIGINT       NOT NULL,
    first_name   VARCHAR(30)  NULL,
    last_name    VARCHAR(30)  NULL,
    phone_number BIGINT       NULL,
    address      VARCHAR(256) NULL,
    PRIMARY KEY (customer_id)
);

INSERT INTO customers
VALUES (1, 'Rick', 'Novak', 123456789, 'Rick Address');
INSERT INTO customers
VALUES (2, 'Susan', 'Connor', 84309595, 'Susan Address');
INSERT INTO customers
VALUES (3, 'Ronald', 'Barr', 94935453546, 'Ronald Address');
INSERT INTO customers
VALUES (4, 'Roger', 'Lum', 754564435, 'Roger Address');
INSERT INTO customers
VALUES (5, 'Jeff', 'Johnson', 976446865, 'Jeff Address');