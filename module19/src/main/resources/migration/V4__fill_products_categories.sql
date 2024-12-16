INSERT INTO categories (name)
VALUES ('Electronics'), ('Books'), ('Clothing'), ('Home & Kitchen'), ('Sports & Outdoors');

INSERT INTO products (name, description)
VALUES ('Smartphone', 'Latest model with advanced features'),
       ('Laptop', 'High performance laptop for work and gaming'),
       ('Fiction Book', 'Bestselling novel by a popular author'),
       ('T-Shirt', 'Comfortable cotton t-shirt'),
       ('Blender', 'Powerful blender for smoothies and more'),
       ('Yoga Mat', 'Non-slip yoga mat for all exercises');

INSERT INTO products_categories (product_id, category_id)
VALUES ((SELECT id FROM products WHERE name='Smartphone'), (SELECT id FROM categories WHERE name='Electronics')),
       ((SELECT id FROM products WHERE name='Laptop'), (SELECT id FROM categories WHERE name='Electronics')),
       ((SELECT id FROM products WHERE name='Fiction Book'), (SELECT id FROM categories WHERE name='Books')),
       ((SELECT id FROM products WHERE name='T-Shirt'), (SELECT id FROM categories WHERE name='Clothing')),
       ((SELECT id FROM products WHERE name='Blender'), (SELECT id FROM categories WHERE name='Home & Kitchen')),
       ((SELECT id FROM products WHERE name='Yoga Mat'), (SELECT id FROM categories WHERE name='Sports & Outdoors'));


