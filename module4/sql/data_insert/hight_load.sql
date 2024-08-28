DO $$
    BEGIN
        FOR i IN 1..100000000 LOOP
                INSERT INTO customers (first_name, last_name, email, tel_number, post_code)
                VALUES (
                           'CustomerFirst' || i,
                           'CustomerLast' || i,
                           'customer' || i || '@example.com',
                           '555-' || LPAD(i::TEXT, 4, '0'),
                           'ZIP' || LPAD((i % 10000)::TEXT, 5, '0')
                       );

                INSERT INTO customer_details (id, date_of_birth, loyalty_points)
                VALUES (
                           currval('seq_customer_id'),
                           '1980-01-01'::DATE + (i % 365 * INTERVAL '1 day'),
                           (i % 1000) + 100
                       );
            END LOOP;
    END $$;

DO $$
    BEGIN
        FOR i IN 1..100 LOOP
                INSERT INTO categories (name)
                VALUES (
                           'Category' || i
                       );
            END LOOP;
    END $$;

DO $$
    DECLARE
        category_id INT;
    BEGIN
        FOR i IN 1..100000 LOOP
                INSERT INTO products (name, description)
                VALUES (
                           'Product' || i,
                           'Description for product ' || i
                       );


                FOR j IN 1..((RANDOM() * 4)::INT + 2) LOOP
                        LOOP
                            category_id := ((RANDOM() * 100)::INT + 1);

                            EXIT WHEN NOT EXISTS (
                                SELECT 1
                                FROM products_categories
                                WHERE product_id = i AND category_id = category_id
                            );

                            INSERT INTO products_categories (product_id, category_id)
                            VALUES (
                                       i,
                                       category_id
                                   );
                        END LOOP;
                    END LOOP;
            END LOOP;
    END $$;


DO $$
    BEGIN
        FOR i IN 1..100000 LOOP
                INSERT INTO products (name, description)
                VALUES (
                           'Product' || i,
                           'Description for product ' || i
                       );
            END LOOP;
    END $$;


DO $$
    BEGIN
        FOR i IN 1..100000000 LOOP
                INSERT INTO orders (order_date, total_price, customer_id)
                VALUES (
                           '2023-01-01'::DATE + ((i-1) % 365),
                           (RANDOM() * 1000)::NUMERIC(10,2),
                           (i % 100000000) + 1
                       );
            END LOOP;
    END $$;

DO $$
    BEGIN
        FOR i IN 10..1000000 LOOP
                INSERT INTO order_items (quantity, order_id, product_id)
                VALUES (
                           (i % 10) + 1,
                           (i % 100000000) + 1,
                           ((i - 1) % 99991) + 10
                       );
            END LOOP;
    END $$;