-- Insert customers
INSERT INTO customers (first_name, last_name, email, tel_number, post_code)
VALUES
('John', 'Doe', 'john.doe@example.com', '555-1234', '90210'),
('Jane', 'Smith', 'jane.smith@example.com', '555-5678', '10001'),
('Alice', 'Johnson', 'alice.johnson@example.com', '555-8765', '30301'),
('Bob', 'Brown', 'bob.brown@example.com', '555-4321', '60601'),
('Carol', 'Davis', 'carol.davis@example.com', '555-0987', '80202'),
('Dave', 'Wilson', 'dave.wilson@example.com', '555-2345', '94105'),
('Eve', 'Miller', 'eve.miller@example.com', '555-6789', '33101'),
('Frank', 'Taylor', 'frank.taylor@example.com', '555-3456', '02108'),
('Grace', 'Lee', 'grace.lee@example.com', '555-7890', '98101'),
('Henry', 'Walker', 'henry.walker@example.com', '555-5670', '75201');

-- Insert customer details
INSERT INTO customer_details (id, date_of_birth, loyalty_points)
VALUES
(1, '1985-04-12', 1500),
(2, '1990-07-23', 2000),
(3, '1978-11-15', 1200),
(4, '1982-01-30', 1800),
(5, '1992-09-08', 2200),
(6, '1987-03-19', 1300),
(7, '1985-06-25', 1450),
(8, '1991-11-07', 1700),
(9, '1984-08-14', 1600),
(10, '1979-12-20', 1400);


-- Insert categories
INSERT INTO categories (name)
VALUES
('Electronics'),
('Books'),
('Clothing'),
('Home & Garden'),
('Sports & Outdoors'),
('Toys & Games'),
('Health & Beauty'),
('Automotive'),
('Music'),
('Office Supplies');


-- Insert products
INSERT INTO products (name, description)
VALUES
('Smartphone', 'Latest model with 5G and advanced camera features.'),
('Laptop', 'High-performance laptop with 16GB RAM and 512GB SSD.'),
('T-shirt', '100% cotton t-shirt in various sizes and colors.'),
('Coffee Maker', 'Automatic drip coffee maker with programmable timer.'),
('Basketball', 'Official size and weight, suitable for indoor/outdoor play.'),
('Novel', 'Bestselling fiction novel by a popular author.'),
('Shampoo', 'Gentle shampoo suitable for all hair types.'),
('Guitar', 'Acoustic guitar with a full-bodied sound.'),
('Desk Chair', 'Ergonomic office chair with lumbar support.'),
('Toy Car', 'Die-cast model car for ages 3 and up.');


-- Insert product-category relationships
INSERT INTO products_categories (product_id, category_id)
VALUES
(1, 1),  -- Smartphone in Electronics
(2, 1),  -- Laptop in Electronics
(3, 3),  -- T-shirt in Clothing
(4, 4),  -- Coffee Maker in Home & Garden
(5, 5),  -- Basketball in Sports & Outdoors
(6, 2),  -- Novel in Books
(7, 7),  -- Shampoo in Health & Beauty
(8, 9),  -- Guitar in Music
(9, 10), -- Desk Chair in Office Supplies
(10, 6); -- Toy Car in Toys & Games


-- Insert orders
INSERT INTO orders (order_date, order_status, total_price, customer_id)
VALUES
('2024-08-25', 'Shipped', 799.99, 1),
('2024-08-26', 'Processing', 199.99, 2),
('2024-08-27', 'Delivered', 24.99, 3),
('2024-08-28', 'Pending', 15.99, 4),
('2024-08-29', 'Cancelled', 0.00, 5),
('2024-08-30', 'Shipped', 59.99, 6),
('2024-08-31', 'Delivered', 149.99, 7),
('2024-09-01', 'Processing', 89.99, 8),
('2024-09-02', 'Pending', 299.99, 9),
('2024-09-03', 'Shipped', 49.99, 10);


-- Insert order items
INSERT INTO order_items (quantity, order_id, product_id)
VALUES
(1, 1, 1),  -- 1 Smartphone in Order 1
(1, 1, 2),  -- 1 Laptop in Order 1
(2, 2, 3),  -- 2 T-shirts in Order 2
(1, 3, 6),  -- 1 Novel in Order 3
(1, 4, 5),  -- 1 Basketball in Order 4
(1, 6, 4),  -- 1 Coffee Maker in Order 6
(3, 7, 7),  -- 3 Shampoos in Order 7
(1, 8, 8),  -- 1 Guitar in Order 8
(2, 9, 9),  -- 2 Desk Chairs in Order 9
(1, 10, 10);-- 1 Toy Car in Order 10
