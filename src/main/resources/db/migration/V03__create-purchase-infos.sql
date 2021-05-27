CREATE TABLE purchaseinfos
(
    purchaseinfo_id BIGINT  NOT NULL,
    employee_id     BIGINT  NOT NULL,
    customer_id     BIGINT  NOT NULL,
    cost            DOUBLE  NULL,
    amount          INTEGER NULL,
    date            DATE    NULL,
    CONSTRAINT purchase_employee_id
        FOREIGN KEY (employee_id)
            REFERENCES shop_management_db.employees (employee_id)
            ON DELETE CASCADE,
    CONSTRAINT purchase_customer_id
        FOREIGN KEY (customer_id)
            REFERENCES shop_management_db.customers (customer_id)
            ON DELETE CASCADE,
    PRIMARY KEY (purchaseinfo_id)
);

INSERT INTO purchaseinfos
VALUES (1, 1, 1, 2, 4, '2020-03-21');
INSERT INTO purchaseinfos
VALUES (2, 2, 2, 3, 5, '2020-05-22');
INSERT INTO purchaseinfos
VALUES (3, 3, 3, 5, 1, '2021-07-02');
INSERT INTO purchaseinfos
VALUES (4, 4, 4, 4, 3, '2020-03-21');
INSERT INTO purchaseinfos
VALUES (5, 5, 5, 1, 2, '2020-03-21');
