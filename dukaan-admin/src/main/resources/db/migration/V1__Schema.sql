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

CREATE TABLE USERS (
    id  varchar(32) primary key,
    email varchar(128) not null unique,
    password varchar(64) not null,
    first_name varchar(45) not null,
    last_name varchar(45) not null,
    photo_name varchar(64),
    is_active boolean not null
);

CREATE TABLE USERS_ROLES (
    user_id varchar(32),
    role_id varchar(32),
    primary key (user_id, role_id),
    foreign key (user_id) references USERS(id),
    foreign key (role_id) references ROLES(id)
);

INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('57964D464FDF4821A1ABEB1C2795883D', 'admin@dukaan.com', 'admin', 'Admin', 'Admin', null, true);

INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('57964D464FDF4821A1ABEB1C2795883D', '611F4954CCE948489D4A89F1842DD00E')