-- ─────────────────────────────────────────────────────────────────────────────
-- Seed data for MySQL (ecomdb.product)
--
-- Column names match the JPA-generated schema (snake_case from camelCase fields).
-- product_available / stock_quantity / release_date come from the entity.
--
-- Run once after the schema is created (spring.jpa.hibernate.ddl-auto=update
-- will create the table on first boot, then this script populates sample data).
-- To run automatically on every start set:
--   spring.sql.init.mode=always
--   spring.sql.init.schema-locations=classpath:schema.sql
--   spring.sql.init.data-locations=classpath:data1.sql
-- ─────────────────────────────────────────────────────────────────────────────

INSERT IGNORE INTO product
    (name, description, brand, price, category, release_date, product_available, stock_quantity, image_name, image_type, image_data)
VALUES
    ('Tata Nexon',         'A compact SUV with excellent safety features and performance.', 'Tata Motors',    750000.00, 'Cars', '2024-01-15', true, 50,  NULL, NULL, NULL),
    ('Maruti Suzuki Swift','A popular hatchback known for its fuel efficiency and reliability.', 'Maruti Suzuki', 550000.00, 'Cars', '2024-02-01', true, 100, NULL, NULL, NULL),
    ('Hyundai Creta',      'A stylish SUV with advanced features and comfortable interior.', 'Hyundai',       950000.00, 'Cars', '2024-03-01', true, 75,  NULL, NULL, NULL),
    ('Mahindra Thar',      'A rugged off-road SUV with a powerful engine and modern amenities.', 'Mahindra',  1200000.00,'Cars', '2024-04-01', true, 30,  NULL, NULL, NULL),
    ('Honda City',         'A premium sedan with a sleek design and advanced safety features.', 'Honda',      1100000.00,'Cars', '2024-05-01', true, 60,  NULL, NULL, NULL);