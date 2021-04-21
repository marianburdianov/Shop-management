CREATE TABLE products
(
    product_id BIGINT      NOT NULL,
    name       VARCHAR(30) NOT NULL,
    price      DOUBLE      NULL,
    quantity   INTEGER     NULL,
    PRIMARY KEY (product_id)
);

INSERT INTO products
VALUES (1, 'Rice', 2, 4);
INSERT INTO products
VALUES (2, 'Pasta', 1, 5);
INSERT INTO products
VALUES (3, 'Biscuits', 3, 2);
INSERT INTO products
VALUES (4, 'Bread', 5, 4);
INSERT INTO products
VALUES (5, 'Chips', 1, 2);