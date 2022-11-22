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
('57964D464FDF4821A1ABEB1C2795883D', 'admin@dukaan.com', '$2a$10$N5CXDLePpRQbcNUcO8qc5OEc4yOwofSsBubaM8Y9aKAVkgrm4cKK6', 'Admin', 'Admin', null, true);

INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('8965E6AC51BD419B85628ED26D1F3A30', 'nam@codejava.net', '$2a$10$nJ7RDIVjUz/ZGgjkKVWjp.0p6Ijt2JzhQ9QpaNp2khlHrFPfLCrsW', 'Nam', 'Ha Minh', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('A1632A9B81344049BFA911549E4CEB57', 'kanna.allada@gmail.com', '$2a$10$GX1Esv/J7pHaClREXRrV/.myaMX08BxrqQs5kzljwyCQLwf8Aa/Z.', 'Allada', 'Pavan', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('8FFC62F9DEFA486A97B5A5291E43043F', 'aecllc.bnk@gmail.com', '$2a$10$AdUkx1ielQbWfFYngypW5uw6W93.dC9ZhZ1tSSvoFkEH/5eRpqzDa', 'Bruce', 'Kitchell', null, false);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('B15C7B5033AB470CBE1147F1E436F5BC', 'muhammad.evran13@gmail.com', '$2a$10$kq95fnTg9y9wYkXn1alum.pxpiRNgoznrf4u8BCrjf6DGdwv3JS.m', 'Muhammad', 'Evran', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('08D628FA100645C5966F3937609FEAFE', 'kent.hervey8@gmail.com', '$2a$10$9o6OHfRqxQIZxXwWL48Eru30Ll3EopEMG3GsWGEWoImhGLAH.IqxG', 'Kent', 'Hervey', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('2C8E2DA3A5E74376B3D195D63DA2E567', 'alfredephraim26@gmail.com', '$2a$10$eXSyarV0aTam9TthN8aeaenVMxJfaT/.9a/ELGZiEQB1Fw6C.66BW', 'Alfred', 'Ephraim', null, false);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('FE766B893B01480CBA2939E6841E3713', 'nathihsa@gmail.com', '$2a$10$t23zcRrGma4Pzeffk9YPWOWdoEGKS4/QjknuL5vLnjilc68xZ6AQO', 'Nathi', 'Sangweni', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('C4153BCB816A469CABF7A46FAA29137A', 'ammanollashirisha2020@gmail.com', '$2a$10$etDcmCe.I450jfxEo36sEu76TuPIiqIs.bk.Wr3P7HhBBfnfNPtG.', 'Ammanolla', 'Shirisha', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('803437F5AA8344FCB83C2D251AB0B2E3', 'bfeeny238@hotmail.com', '$2a$10$SqRSV0zSe.XKVkxC0ABi2.11a5VMJoTOQLjDIVRkKlmsUbw4G9XQe', 'Bill', 'Feeny', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('AFDE0F087F7D4DD48908E8ED8BF5617D', 'redsantosph@gmail.com', '$2a$10$r.6n0E81s51Bb2XgjL4OyeWahYr/DtSvxts6ybo5QlRArIKu08wKG', 'Frederick', 'Santos', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('873908066D37405BADC58FB2CAE17AC7', 'ro_anamaria@mail.ru', '$2a$10$J/i2afsvYD1nuqAd703AvuNRm4z29YMrdyj76lq6rntN/RkiV7tJi', 'Ana', 'Maria', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('C14E331192E14B1899D69054B55C55E2', 'maytassatya@hotmail.com', '$2a$10$VEdLzQp8ZdhtvRC0OLd6uOWD1sd1Lx6eTTZmQJkiAQnWRaqCb7UXW', 'Satya', 'Narayana', null, false);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('41E6E6CDDF03411EAE1C777597948C55', 'matthewefoli@gmail.com', '$2a$10$mwRDmvUSvz8WWCcyjJal2u.wPB4nbudB0lMsH949MQx29hsMhHzPy', 'Matthew', 'Efoli', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('4751545F8DE5493D996CECF41BED9799', 'davekumara2@gmail.com', '$2a$10$0x/vm8uOxY5CckToFhnPMOJuJii74h5oE3Pc1y7lmxmh.u2HUCSxq', 'Dave', 'Kumar', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('5A7F98DD06654F2AB326BFE7090E3923', 'jackkbruce@yahoo.com', '$2a$10$udA3NIo1kq0LC4eojCS6GuKtrWONzJQrzKo2llJ6r0dlCZ55oNjnO', 'Jack', 'Bruce', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('9A47581088D54C64B4FF1D80762D7F03', 'zirri.mohamed@gmail.com', '$2a$10$J5oEQW5k30cVf8YF2RkHp.1UruzgX6qFkIqM2NN1ZXoqJ/4C8nfOu', 'Mohamed', 'Zirri', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('DDE22A29767243689D97B1A7A4095922', 'mk.majumdar009@hotmail.com', '$2a$10$Ng8XM4MSRgUrP00qKyoFdOv1rqceTdDwdZfIDP29plEX639HI4OlK', 'Mithun', 'Majumdar', null, false);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('2DB2C51C9D264B37B4776351B26829FD', 'aliraza.arain.28@gmail.com', '$2a$10$nGsS1M.8eORkum3zDd/6zuuzwZbUbX7CQW9vhD6aaXLEEuafg/Ca6', 'Ali', 'Raza', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('79066D5F7EC14858860ED7E309F57433', 'isaachenry2877@gmail.com', '$2a$10$T6vOnoNjBEAgR8wJwRNml.MIc//I6AKSmbZ.JGQjpol8pgvCIWwzO', 'Isaac', 'Henry', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('AF2C2F8DAEC6465DA2747B5F7C76A740', 's.stasovska82@hotmail.com', '$2a$10$5QwWb0a3nYvPmuOnG2/u3.2gv5Djwz8.t4m.Bgyhvqcw4dE8QVKDm', 'Svetlana', 'Stasovska', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('545D690FC1D0446BAB1653A0100C87C2', 'mikegates2012@gmail.com', '$2a$10$VNPRN/YkicjXlDMZsONT2OkExWDtkS/GOLacmBKewWih.tNQdFtRy', 'Mike', 'Gates', null, true);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('DDD4D41268F74CF7BC8891B04D532FDC', 'pedroquintero67@gmail.com', '$2a$10$VqD6yx8AcGH7fc6rMgcIPuPz5kSMWzh.dxBf7LQSciNUqyls94A5y', 'Pedro', 'Quintero', null, false);
INSERT INTO USERS(id, email, password, first_name, last_name, photo_name, is_active) VALUES
('562F331C3C0D4D3A8518A2EF1DB51E42', 'amina.elshal2@yahoo.com', '$2a$10$tb/gmnpakpEX67CnF08Yquc0/gSOtfZ0YUeiaPsWBsH2YWaNabcu2', 'Amina', 'Elshal', null, true);

INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('57964D464FDF4821A1ABEB1C2795883D', '611F4954CCE948489D4A89F1842DD00E');

INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('8965E6AC51BD419B85628ED26D1F3A30', '611F4954CCE948489D4A89F1842DD00E');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('A1632A9B81344049BFA911549E4CEB57', '611F4954CCE948489D4A89F1842DD00E');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('8FFC62F9DEFA486A97B5A5291E43043F', '611F4954CCE948489D4A89F1842DD00E');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('B15C7B5033AB470CBE1147F1E436F5BC', '0BD5E6681ABA416D8573631C3323CCB1');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('08D628FA100645C5966F3937609FEAFE', '0BD5E6681ABA416D8573631C3323CCB1');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('08D628FA100645C5966F3937609FEAFE', '45FB6462017E4C528A01BA6A0F0185B3');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('2C8E2DA3A5E74376B3D195D63DA2E567', '0BD5E6681ABA416D8573631C3323CCB1');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('FE766B893B01480CBA2939E6841E3713', '0BD5E6681ABA416D8573631C3323CCB1');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('C4153BCB816A469CABF7A46FAA29137A', '0BD5E6681ABA416D8573631C3323CCB1');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('803437F5AA8344FCB83C2D251AB0B2E3', '2631B58952C04F1F9D379495603B1291');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('AFDE0F087F7D4DD48908E8ED8BF5617D', '2631B58952C04F1F9D379495603B1291');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('873908066D37405BADC58FB2CAE17AC7', '0BD5E6681ABA416D8573631C3323CCB1');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('873908066D37405BADC58FB2CAE17AC7', '2631B58952C04F1F9D379495603B1291');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('C14E331192E14B1899D69054B55C55E2', '2631B58952C04F1F9D379495603B1291');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('41E6E6CDDF03411EAE1C777597948C55', '2631B58952C04F1F9D379495603B1291');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('4751545F8DE5493D996CECF41BED9799', '611F4954CCE948489D4A89F1842DD00E');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('4751545F8DE5493D996CECF41BED9799', 'DC53FDA5ECCA4B81AF6C8718B81450F0');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('57964D464FDF4821A1ABEB1C2795883D', '45FB6462017E4C528A01BA6A0F0185B3');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('5A7F98DD06654F2AB326BFE7090E3923', 'DC53FDA5ECCA4B81AF6C8718B81450F0');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('5A7F98DD06654F2AB326BFE7090E3923', '0BD5E6681ABA416D8573631C3323CCB1');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('9A47581088D54C64B4FF1D80762D7F03', 'DC53FDA5ECCA4B81AF6C8718B81450F0');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('DDE22A29767243689D97B1A7A4095922', 'DC53FDA5ECCA4B81AF6C8718B81450F0');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('2DB2C51C9D264B37B4776351B26829FD', 'DC53FDA5ECCA4B81AF6C8718B81450F0');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('2DB2C51C9D264B37B4776351B26829FD', '0BD5E6681ABA416D8573631C3323CCB1');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('79066D5F7EC14858860ED7E309F57433', '45FB6462017E4C528A01BA6A0F0185B3');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('79066D5F7EC14858860ED7E309F57433', '2631B58952C04F1F9D379495603B1291');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('AF2C2F8DAEC6465DA2747B5F7C76A740', '45FB6462017E4C528A01BA6A0F0185B3');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('AF2C2F8DAEC6465DA2747B5F7C76A740', '0BD5E6681ABA416D8573631C3323CCB1');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('AF2C2F8DAEC6465DA2747B5F7C76A740', '2631B58952C04F1F9D379495603B1291');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('545D690FC1D0446BAB1653A0100C87C2', '45FB6462017E4C528A01BA6A0F0185B3');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('DDD4D41268F74CF7BC8891B04D532FDC', '45FB6462017E4C528A01BA6A0F0185B3');
INSERT INTO USERS_ROLES(user_id, role_id) VALUES
('562F331C3C0D4D3A8518A2EF1DB51E42', '45FB6462017E4C528A01BA6A0F0185B3');