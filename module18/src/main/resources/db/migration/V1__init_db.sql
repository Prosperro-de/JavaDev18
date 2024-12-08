CREATE SEQUENCE IF NOT EXISTS seq_customer_id
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS seq_orders_id
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS seq_products_id
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS seq_categories_id
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS seq_order_items_id
    START WITH 1
    INCREMENT BY 1;


CREATE TABLE IF NOT EXISTS customers
(
    id         BIGINT DEFAULT nextval('seq_customer_id'),
    first_name VARCHAR(255),
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(50) UNIQUE,
    tel_number VARCHAR(50),
    post_code  VARCHAR(10),
    CONSTRAINT pk_customers_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS customer_details
(
    id             BIGINT,
    date_of_birth  DATE,
    loyalty_points INT,
    CONSTRAINT pk_customer_details_id PRIMARY KEY (id),
    CONSTRAINT fk_customer_id FOREIGN KEY (id) references customers (id)
);

CREATE TABLE IF NOT EXISTS orders
(
    id          BIGINT DEFAULT nextval('seq_orders_id'),
    order_date  DATE DEFAULT now(),
    order_status VARCHAR(10),
    total_price DECIMAL(10, 2),
    customer_id BIGINT,
    CONSTRAINT pk_orders_id PRIMARY KEY (id),
    CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES customers (id)
);

CREATE TABLE IF NOT EXISTS products
(
    id          BIGINT DEFAULT nextval('seq_products_id'),
    name        VARCHAR(255),
    description VARCHAR(255),
    CONSTRAINT pk_products_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS categories
(
    id   BIGINT DEFAULT nextval('seq_categories_id'),
    name VARCHAR(255),
    CONSTRAINT pk_categories_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS products_categories
(
    product_id  BIGINT,
    category_id BIGINT,
    CONSTRAINT pk_product_categories_id PRIMARY KEY (product_id, category_id),
    CONSTRAINT fk_products_id FOREIGN KEY (product_id) REFERENCES products (id),
    CONSTRAINT fk_categories_id FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE IF NOT EXISTS order_items
(
    id         BIGINT DEFAULT nextval('seq_order_items_id'),
    quantity   INT,
    order_id   BIGINT,
    product_id BIGINT,
    CONSTRAINT pk_order_items_id PRIMARY KEY (id),
    CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT fk_products_id FOREIGN KEY (product_id) REFERENCES products (id)
);


CREATE INDEX order_customer_idx ON orders(customer_id);
CREATE INDEX products_categories_product_idx ON products_categories(product_id);
CREATE INDEX products_categories_categories_idx ON products_categories(category_id);
CREATE INDEX order_item_product_idx ON order_items(product_id);
CREATE INDEX order_item_order_idx ON order_items(order_id);
