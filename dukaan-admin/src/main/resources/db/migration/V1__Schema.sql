CREATE TABLE ROLES (
    id  varchar(32) primary key,
    name varchar(40) not null unique,
    description varchar(150) not null
);

INSERT INTO ROLES(id, name, description) VALUES
('611F4954CCE948489D4A89F1842DD00E', 'Admin', 'manage everything'),
('2631B58952C04F1F9D379495603B1291', 'Seller', 'manage product price, customers, shipping, orders, sales reports'),
('0BD5E6681ABA416D8573631C3323CCB1', 'Editor', 'manage categories, brands, products, articles and menus'),
('DC53FDA5ECCA4B81AF6C8718B81450F0', 'Shipper', 'view products, view orders and update order status'),
('45FB6462017E4C528A01BA6A0F0185B3', 'Assistant', 'manage questions and reviews');