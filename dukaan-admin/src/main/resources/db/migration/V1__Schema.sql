CREATE TABLE ROLES (
    id  varchar(50) primary key,
    name varchar(40) not null unique,
    description varchar(150) not null
);

CREATE TABLE USERS (
    id  varchar(50) primary key,
    email varchar(128) not null unique,
    password varchar(64) not null,
    first_name varchar(45) not null,
    last_name varchar(45) not null,
    photo_name varchar(64),
    is_active boolean not null
);

CREATE TABLE USERS_ROLES (
    user_id varchar(50),
    role_id varchar(50),
    primary key (user_id, role_id),
    foreign key (user_id) references USERS(id),
    foreign key (role_id) references ROLES(id)
);

CREATE TABLE CATEGORIES (
    id  varchar(50) primary key,
    name varchar(120) not null unique,
    alias varchar(64) not null unique,
    image varchar(64),
    is_active boolean not null,
    parent_id varchar(50),
    foreign key (parent_id) references CATEGORIES(id)
);

CREATE TABLE BRANDS (
    id  varchar(50) primary key,
    name varchar(50) not null unique,
    logo varchar(64) not null
);

CREATE TABLE BRANDS_CATEGORIES (
    brand_id varchar(50),
    category_id varchar(50),
    primary key (brand_id, category_id),
    foreign key (brand_id) references BRANDS(id),
    foreign key (category_id) references CATEGORIES(id)
);


-------- Add Data --------

INSERT INTO ROLES(id, name, description) VALUES
('ce90fe6b-01ed-4bac-9961-f13b505a1758', 'Admin', 'manage everything'),
('b2b17514-04a8-4bde-8430-236d644f3769', 'Seller', 'manage product price, customers, shipping, orders, sales reports'),
('2a6a692f-b699-4384-90a1-61e7850f2130', 'Editor', 'manage categories, brands, products, articles and menus'),
('6683523a-651d-4286-a02d-86a7326b9fe8', 'Shipper', 'view products, view orders and update order status'),
('099b2f60-5049-4c5c-8307-3d8be1fded46', 'Assistant', 'manage questions and reviews')
;

INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('c50c2dde-9265-4986-ab09-5cad594c9513', 'admin@dukaan.com', '$2a$10$N5CXDLePpRQbcNUcO8qc5OEc4yOwofSsBubaM8Y9aKAVkgrm4cKK6', 'Admin', 'Admin', null, true),
('d23c1693-3229-4d74-8ce7-077c4ef2e35a', 'nam@codejava.net', '$2a$10$nJ7RDIVjUz/ZGgjkKVWjp.0p6Ijt2JzhQ9QpaNp2khlHrFPfLCrsW', 'Nam', 'Ha Minh', null, true),
('cbc2e3b3-2a4c-4189-adac-085ab6cce47c', 'kanna.allada@gmail.com', '$2a$10$GX1Esv/J7pHaClREXRrV/.myaMX08BxrqQs5kzljwyCQLwf8Aa/Z.', 'Allada', 'Pavan', null, true),
('ab32d9fc-cd43-4c3b-a47a-d89f2a3e747e', 'aecllc.bnk@gmail.com', '$2a$10$AdUkx1ielQbWfFYngypW5uw6W93.dC9ZhZ1tSSvoFkEH/5eRpqzDa', 'Bruce', 'Kitchell', null, false),
('df104501-5913-4721-91a3-61bc53f9e0ba', 'muhammad.evran13@gmail.com', '$2a$10$kq95fnTg9y9wYkXn1alum.pxpiRNgoznrf4u8BCrjf6DGdwv3JS.m', 'Muhammad', 'Evran', null, true),
('010504e5-436f-495e-ae2a-c60c26e44033', 'kent.hervey8@gmail.com', '$2a$10$9o6OHfRqxQIZxXwWL48Eru30Ll3EopEMG3GsWGEWoImhGLAH.IqxG', 'Kent', 'Hervey', null, true),
('e902bd0c-19cf-40c5-ade9-0a3f3ca6710b', 'alfredephraim26@gmail.com', '$2a$10$eXSyarV0aTam9TthN8aeaenVMxJfaT/.9a/ELGZiEQB1Fw6C.66BW', 'Alfred', 'Ephraim', null, false),
('0c2aa217-2c76-4008-b633-80649afc5516', 'nathihsa@gmail.com', '$2a$10$t23zcRrGma4Pzeffk9YPWOWdoEGKS4/QjknuL5vLnjilc68xZ6AQO', 'Nathi', 'Sangweni', null, true),
('8db90d32-bac9-4896-b610-ec4f384e8ec2', 'ammanollashirisha2020@gmail.com', '$2a$10$etDcmCe.I450jfxEo36sEu76TuPIiqIs.bk.Wr3P7HhBBfnfNPtG.', 'Ammanolla', 'Shirisha', null, true),
('219e3b76-be51-4c4c-a9d8-e44d115db6d8', 'bfeeny238@hotmail.com', '$2a$10$SqRSV0zSe.XKVkxC0ABi2.11a5VMJoTOQLjDIVRkKlmsUbw4G9XQe', 'Bill', 'Feeny', null, true),
('a69a8df2-5566-4355-99ed-fef128cda96a', 'redsantosph@gmail.com', '$2a$10$r.6n0E81s51Bb2XgjL4OyeWahYr/DtSvxts6ybo5QlRArIKu08wKG', 'Frederick', 'Santos', null, true),
('69abf956-49d8-4dd8-9ecf-8b2d8616f2f6', 'ro_anamaria@mail.ru', '$2a$10$J/i2afsvYD1nuqAd703AvuNRm4z29YMrdyj76lq6rntN/RkiV7tJi', 'Ana', 'Maria', null, true),
('e7968cae-de20-40da-94d2-9b3c7535baba', 'maytassatya@hotmail.com', '$2a$10$VEdLzQp8ZdhtvRC0OLd6uOWD1sd1Lx6eTTZmQJkiAQnWRaqCb7UXW', 'Satya', 'Narayana', null, false),
('ac325eb9-a1ae-46a1-9146-e64eca0da57a', 'matthewefoli@gmail.com', '$2a$10$mwRDmvUSvz8WWCcyjJal2u.wPB4nbudB0lMsH949MQx29hsMhHzPy', 'Matthew', 'Efoli', null, true),
('38b804be-2855-43ed-a0ee-343a95685a1e', 'davekumara2@gmail.com', '$2a$10$0x/vm8uOxY5CckToFhnPMOJuJii74h5oE3Pc1y7lmxmh.u2HUCSxq', 'Dave', 'Kumar', null, true),
('533603ad-78b7-4e21-9627-0d935b81b838', 'jackkbruce@yahoo.com', '$2a$10$udA3NIo1kq0LC4eojCS6GuKtrWONzJQrzKo2llJ6r0dlCZ55oNjnO', 'Jack', 'Bruce', null, true),
('09a0a78f-21b1-410d-b918-b7e5d470e727', 'zirri.mohamed@gmail.com', '$2a$10$J5oEQW5k30cVf8YF2RkHp.1UruzgX6qFkIqM2NN1ZXoqJ/4C8nfOu', 'Mohamed', 'Zirri', null, true),
('5a6c8ed7-47eb-417e-a452-ab8882ce7e41', 'mk.majumdar009@hotmail.com', '$2a$10$Ng8XM4MSRgUrP00qKyoFdOv1rqceTdDwdZfIDP29plEX639HI4OlK', 'Mithun', 'Majumdar', null, false),
('d17fd461-6e3c-46c3-912b-12c4e72b1ebf', 'aliraza.arain.28@gmail.com', '$2a$10$nGsS1M.8eORkum3zDd/6zuuzwZbUbX7CQW9vhD6aaXLEEuafg/Ca6', 'Ali', 'Raza', null, true),
('0986263e-a710-4bb8-bed0-361c4642a550', 'isaachenry2877@gmail.com', '$2a$10$T6vOnoNjBEAgR8wJwRNml.MIc//I6AKSmbZ.JGQjpol8pgvCIWwzO', 'Isaac', 'Henry', null, true),
('1eed1cae-2ecf-4e7f-8880-030765e8c118', 's.stasovska82@hotmail.com', '$2a$10$5QwWb0a3nYvPmuOnG2/u3.2gv5Djwz8.t4m.Bgyhvqcw4dE8QVKDm', 'Svetlana', 'Stasovska', null, true),
('a5da9845-2874-453c-ada3-f436ff53c5c7', 'mikegates2012@gmail.com', '$2a$10$VNPRN/YkicjXlDMZsONT2OkExWDtkS/GOLacmBKewWih.tNQdFtRy', 'Mike', 'Gates', null, true),
('d1d2b437-d521-40ce-9854-7ed68b7845c0', 'pedroquintero67@gmail.com', '$2a$10$VqD6yx8AcGH7fc6rMgcIPuPz5kSMWzh.dxBf7LQSciNUqyls94A5y', 'Pedro', 'Quintero', null, false),
('da4a1e10-7ca0-4a65-a88d-91368f1d8a19', 'amina.elshal2@yahoo.com', '$2a$10$tb/gmnpakpEX67CnF08Yquc0/gSOtfZ0YUeiaPsWBsH2YWaNabcu2', 'Amina', 'Elshal', null, true)
;

INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('c50c2dde-9265-4986-ab09-5cad594c9513', 'ce90fe6b-01ed-4bac-9961-f13b505a1758'),
('d23c1693-3229-4d74-8ce7-077c4ef2e35a', 'ce90fe6b-01ed-4bac-9961-f13b505a1758'),
('cbc2e3b3-2a4c-4189-adac-085ab6cce47c', 'ce90fe6b-01ed-4bac-9961-f13b505a1758'),
('ab32d9fc-cd43-4c3b-a47a-d89f2a3e747e', 'ce90fe6b-01ed-4bac-9961-f13b505a1758'),
('df104501-5913-4721-91a3-61bc53f9e0ba', '2a6a692f-b699-4384-90a1-61e7850f2130'),
('010504e5-436f-495e-ae2a-c60c26e44033', '2a6a692f-b699-4384-90a1-61e7850f2130'),
('010504e5-436f-495e-ae2a-c60c26e44033', '099b2f60-5049-4c5c-8307-3d8be1fded46'),
('e902bd0c-19cf-40c5-ade9-0a3f3ca6710b', '2a6a692f-b699-4384-90a1-61e7850f2130'),
('0c2aa217-2c76-4008-b633-80649afc5516', '2a6a692f-b699-4384-90a1-61e7850f2130'),
('8db90d32-bac9-4896-b610-ec4f384e8ec2', '2a6a692f-b699-4384-90a1-61e7850f2130'),
('219e3b76-be51-4c4c-a9d8-e44d115db6d8', 'b2b17514-04a8-4bde-8430-236d644f3769'),
('a69a8df2-5566-4355-99ed-fef128cda96a', 'b2b17514-04a8-4bde-8430-236d644f3769'),
('69abf956-49d8-4dd8-9ecf-8b2d8616f2f6', '2a6a692f-b699-4384-90a1-61e7850f2130'),
('69abf956-49d8-4dd8-9ecf-8b2d8616f2f6', 'b2b17514-04a8-4bde-8430-236d644f3769'),
('e7968cae-de20-40da-94d2-9b3c7535baba', 'b2b17514-04a8-4bde-8430-236d644f3769'),
('ac325eb9-a1ae-46a1-9146-e64eca0da57a', 'b2b17514-04a8-4bde-8430-236d644f3769'),
('38b804be-2855-43ed-a0ee-343a95685a1e', 'ce90fe6b-01ed-4bac-9961-f13b505a1758'),
('38b804be-2855-43ed-a0ee-343a95685a1e', '6683523a-651d-4286-a02d-86a7326b9fe8'),
('c50c2dde-9265-4986-ab09-5cad594c9513', '099b2f60-5049-4c5c-8307-3d8be1fded46'),
('533603ad-78b7-4e21-9627-0d935b81b838', '6683523a-651d-4286-a02d-86a7326b9fe8'),
('533603ad-78b7-4e21-9627-0d935b81b838', '2a6a692f-b699-4384-90a1-61e7850f2130'),
('09a0a78f-21b1-410d-b918-b7e5d470e727', '6683523a-651d-4286-a02d-86a7326b9fe8'),
('5a6c8ed7-47eb-417e-a452-ab8882ce7e41', '6683523a-651d-4286-a02d-86a7326b9fe8'),
('d17fd461-6e3c-46c3-912b-12c4e72b1ebf', '6683523a-651d-4286-a02d-86a7326b9fe8'),
('d17fd461-6e3c-46c3-912b-12c4e72b1ebf', '2a6a692f-b699-4384-90a1-61e7850f2130'),
('0986263e-a710-4bb8-bed0-361c4642a550', '099b2f60-5049-4c5c-8307-3d8be1fded46'),
('0986263e-a710-4bb8-bed0-361c4642a550', 'b2b17514-04a8-4bde-8430-236d644f3769'),
('1eed1cae-2ecf-4e7f-8880-030765e8c118', '099b2f60-5049-4c5c-8307-3d8be1fded46'),
('1eed1cae-2ecf-4e7f-8880-030765e8c118', '2a6a692f-b699-4384-90a1-61e7850f2130'),
('1eed1cae-2ecf-4e7f-8880-030765e8c118', 'b2b17514-04a8-4bde-8430-236d644f3769'),
('a5da9845-2874-453c-ada3-f436ff53c5c7', '099b2f60-5049-4c5c-8307-3d8be1fded46'),
('d1d2b437-d521-40ce-9854-7ed68b7845c0', '099b2f60-5049-4c5c-8307-3d8be1fded46'),
('da4a1e10-7ca0-4a65-a88d-91368f1d8a19', '099b2f60-5049-4c5c-8307-3d8be1fded46')
;


INSERT INTO CATEGORIES(id, name, alias, image, is_active, parent_id) VALUES
('b2e8500e-5b61-48d5-b2c2-c867e2bbfa24','Electronics','electronics','electronics.png',true,NULL),
('845b23f1-c0ee-462d-9fc7-d972ead9e37a','Camera & Photo','camera','camera.jpg',true,'b2e8500e-5b61-48d5-b2c2-c867e2bbfa24'),
('f1c0f5b4-072d-41ce-8d77-3d10b848ef6a','Computers','computers','computers.png',true,NULL),
('d3695de8-0a03-4e61-a897-14ca1182d3da','Cell Phones & Accessories','cellphones','cellphones.png',true,'b2e8500e-5b61-48d5-b2c2-c867e2bbfa24'),
('d2a4d24b-7de7-4016-a8b5-d208bf8bbb37','Desktops','desktop_computers','desktop-computers.png',true,'f1c0f5b4-072d-41ce-8d77-3d10b848ef6a'),
('90867ce7-7355-430f-a26c-d332a1ba2901','Laptops','laptop_computers','laptop-computers.png',true,'f1c0f5b4-072d-41ce-8d77-3d10b848ef6a'),
('c38bdaaa-cb09-4adb-85af-6013fdbec881','Tablets','tablet_computers','tablets.png',true,'f1c0f5b4-072d-41ce-8d77-3d10b848ef6a'),
('6b7e60d7-f156-4d5a-8748-23dc2e614258','Computer Components','computer_components','computer-components.png',true,'f1c0f5b4-072d-41ce-8d77-3d10b848ef6a'),
('e94f33d9-5cb0-48e7-b7bb-f452fa41132c','Bags & Cases','camera_bags_cases','bags-cases.png',true,'845b23f1-c0ee-462d-9fc7-d972ead9e37a'),
('aa904214-6449-4eff-af32-edc517608656','Digital Cameras','digital_cameras','digital-cameras.png',true,'845b23f1-c0ee-462d-9fc7-d972ead9e37a'),
('aff762be-f569-4ba6-b334-ad33a37cbf77','Flashes','camera_flashes','flashes.png',true,'845b23f1-c0ee-462d-9fc7-d972ead9e37a'),
('cae28317-e94d-46dd-8524-533ead7d39ba','Lenses','camera_lenses','lenses.png',true,'845b23f1-c0ee-462d-9fc7-d972ead9e37a'),
('9c1b67ef-5bee-476a-b85f-ccc80e087263','Tripods & Monopods','camera_tripods_monopods','tripods-monopods.png',true,'845b23f1-c0ee-462d-9fc7-d972ead9e37a'),
('168c4292-36a8-4f16-a3d4-ece487d2bfaf','Carrier Cell Phones','carrier_cellphones','carrier-cellphones.png',true,'d3695de8-0a03-4e61-a897-14ca1182d3da'),
('3b76c144-b85a-4cde-831e-e0af0699fa3c','Unlocked Cell Phones','unlocked_cellphones','unlocked-cellphones.png',true,'d3695de8-0a03-4e61-a897-14ca1182d3da'),
('3d29753f-0975-4866-b46a-1dec875565be','Accessories','cellphone_accessories','cellphone-accessories.png',true,'d3695de8-0a03-4e61-a897-14ca1182d3da'),
('b20b0b01-ca53-402b-ad10-b6a87343a0c4','Cables & Adapters','cellphone_cables_adapters','cables-adapters.png',true,'3d29753f-0975-4866-b46a-1dec875565be'),
('63b1f492-11df-4baa-b42f-0dccf7829099','MicroSD Cards','microsd_cards','microsd-cards.png',true,'3d29753f-0975-4866-b46a-1dec875565be'),
('71fb9423-4e74-46a6-9204-43c602f3721e','Stands','cellphone_stands','cellphone-stands.png',true,'3d29753f-0975-4866-b46a-1dec875565be'),
('1c783726-fe4d-4b70-a23a-e434a1d2e02b','Cases','cellphone_cases','cellphone-cases.png',true,'3d29753f-0975-4866-b46a-1dec875565be'),
('6df876ca-7650-43f0-9062-966e2241b55b','Headphones','headphones','headphones.png',true,'3d29753f-0975-4866-b46a-1dec875565be'),
('aba40445-81ab-4b80-ad4d-57b291ebca2a','CPU Processors Unit','computer_processors','computer-processors.png',true,'6b7e60d7-f156-4d5a-8748-23dc2e614258'),
('a5591b0d-ee85-46f6-8fea-1ea7afe6f9d6','Graphic Cards','computer_graphic_cards','graphic-cards.png',true,'6b7e60d7-f156-4d5a-8748-23dc2e614258'),
('bde29811-e6c1-4acb-bfa4-77391c6fa448','Internal Hard Drives','hard_drive','internal-hard-drive.png',true,'6b7e60d7-f156-4d5a-8748-23dc2e614258'),
('84b9d18c-59af-4869-9a55-78a8b2fef6bb','Internal Optical Drives','computer_optical_drives','internal-optical-drives.png',true,'6b7e60d7-f156-4d5a-8748-23dc2e614258'),
('9dc72a66-690e-40dc-90b9-f09820bd61e6','Power Supplies','computer_power_supplies','power-supplies.png',true,'6b7e60d7-f156-4d5a-8748-23dc2e614258'),
('35e98863-9ad8-4b18-8334-c933ee86d6dc','Solid State Drives','solid_state_drives','solid-state-drives.png',true,'6b7e60d7-f156-4d5a-8748-23dc2e614258'),
('bde23f22-b891-4f96-8412-506864e2e350','Sound Cards','computer_sound_cards','sound-cards.png',true,'6b7e60d7-f156-4d5a-8748-23dc2e614258'),
('6bed14f5-ac81-4168-bb6c-c822852d4232','Memory','computer_memory','computer-memory.png',true,'6b7e60d7-f156-4d5a-8748-23dc2e614258'),
('eb884009-692b-4ac7-b76e-f69db9bdfdfd','Motherboard','computer_motherboard','motherboards.png',true,'6b7e60d7-f156-4d5a-8748-23dc2e614258'),
('b523d5e1-6f85-44f7-85e9-c385c99e024f','Network Cards','computer_network_cards','network-cards.png',true,'6b7e60d7-f156-4d5a-8748-23dc2e614258')
;