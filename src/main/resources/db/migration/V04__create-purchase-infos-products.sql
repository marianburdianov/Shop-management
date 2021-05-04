CREATE TABLE purchaseinfos_products
(
    purchaseinfo_id BIGINT,
    product_id      BIGINT,
    unique (purchaseinfo_id, product_id),
    CONSTRAINT fk_purchase_id
        FOREIGN KEY (purchaseinfo_id)
            REFERENCES shop_management_db.purchaseinfos (purchaseinfo_id)
            ON DELETE SET NULL
            ON UPDATE SET NULL,
    CONSTRAINT fk_product_id
        FOREIGN KEY (product_id)
            REFERENCES shop_management_db.products (product_id)
            ON DELETE SET NULL
            ON UPDATE SET NULL
);

INSERT INTO purchaseinfos_products
VALUES (1, 1);
INSERT INTO purchaseinfos_products
VALUES (1, 2);
INSERT INTO purchaseinfos_products
VALUES (2, 2);
INSERT INTO purchaseinfos_products
VALUES (2, 3);
INSERT INTO purchaseinfos_products
VALUES (3, 4);
INSERT INTO purchaseinfos_products
VALUES (3, 5);
INSERT INTO purchaseinfos_products
VALUES (4, 1);
INSERT INTO purchaseinfos_products
VALUES (4, 2);
INSERT INTO purchaseinfos_products
VALUES (5, 3);
INSERT INTO purchaseinfos_products
VALUES (5, 4);