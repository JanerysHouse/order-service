-- liquibase formatted sql
-- changeset janeryshouse:1740244569891-1
CREATE TABLE IF NOT EXISTS orders
(
    id           UUID NOT NULL,
    order_number VARCHAR(255),
    sku_code     VARCHAR(255),
    price        DECIMAL,
    quantity     INTEGER,
    CONSTRAINT pk_order PRIMARY KEY (id)
);

