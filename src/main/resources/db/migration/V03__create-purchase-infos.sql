CREATE TABLE purchaseinfos
(
    purchaseinfo_id BIGINT  NOT NULL,
    employee_id     BIGINT  NOT NULL,
    customer_id     BIGINT  NOT NULL,
    product_id      BIGINT  NOT NULL,
    cost            DOUBLE  NULL,
    amount          INTEGER NULL,
    date            DATE    NULL,
    PRIMARY KEY (purchaseinfo_id),
    CONSTRAINT purchase_employee_id
        FOREIGN KEY (employee_id)
            REFERENCES shop_management_db.employees (employee_id),
    CONSTRAINT purchase_customer_id
        FOREIGN KEY (customer_id)
            REFERENCES shop_management_db.customers (customer_id),
    CONSTRAINT purchase_product_id
        FOREIGN KEY (product_id)
            REFERENCES shop_management_db.products (product_id)
);

INSERT INTO purchaseinfos
VALUES (1, 5, 3, 2, 250, 4, '2020-03-21');
INSERT INTO purchaseinfos
VALUES (2, 4, 1, 3, 180, 5, '2020-05-22');
INSERT INTO purchaseinfos
VALUES (3, 2, 3, 5, 110, 1, '2021-07-02');
INSERT INTO purchaseinfos
VALUES (4, 3, 5, 4, 220, 3, '2020-03-21');
INSERT INTO purchaseinfos
VALUES (5, 5, 3, 1, 80, 2, '2020-03-21');