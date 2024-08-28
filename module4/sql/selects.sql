-- plain select for all elements
SELECT * FROM customers;

-- select for particular elements
SELECT c.last_name, c.first_name FROM customers c;

-- select for particular elements ordered
SELECT c.last_name, c.first_name FROM customers c
ORDER BY c.last_name;

-- select for particular elements reverse ordered
SELECT c.last_name, c.first_name FROM customers c
ORDER BY c.last_name DESC;

-- select particular elements from different tables
SELECT o.total_price, c.last_name, c.email FROM orders o
                                                    JOIN customers c ON c.id = o.customer_id;

-- select particular elements from different tables
SELECT p.name as product_name, c.first_name as customer_name, o.total_price, oi.quantity
FROM order_items oi
         LEFT JOIN public.orders o on oi.order_id = o.id
         LEFT JOIN public.customers c on c.id = o.customer_id
         LEFT JOIN public.products p on p.id = oi.product_id;

-- get max order price
SELECT max(o.total_price) FROM orders o;

-- get loyalty points for customers have placed order
SELECT c.first_name, cd.loyalty_points FROM customers c
                                                JOIN customer_details cd on c.id = cd.id
WHERE EXISTS(SELECT * FROM orders where customer_id = c.id);


SELECT oi.id FROM order_items oi;

-- select only products are already ordered
SELECT p.id, p.description FROM products p
WHERE p.id IN (SELECT oi.product_id FROM order_items oi);

-- select for customers with particular name
SELECT * from customers
WHERE first_name = 'John';

-- select for customers name starts with L
SELECT * FROM customers
WHERE first_name LIKE 'L%';

-- select orders with assigned customers
SELECT c.first_name, c.last_name FROM orders o
                                          JOIN public.customers c on c.id = o.customer_id
WHERE o.customer_id IS NOT NULL;

-- select customers and count amount of orders already places
SELECT c.first_name, c.last_name, COUNT(o.id) as order_count FROM customers c
                                                                      JOIN orders o on c.id = o.customer_id
GROUP BY c.first_name, c.last_name;

-- select products and sum how many times appeared as part of order
SELECT p.name, SUM(oi.quantity) FROM order_items oi
                                         JOIN products p on p.id = oi.product_id
GROUP BY p.name
ORDER BY p.name;