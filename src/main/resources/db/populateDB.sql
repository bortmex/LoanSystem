DELETE FROM user_roles;
DELETE FROM credit_application_list_product;
DELETE FROM credit_application;
DELETE FROM products;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('User1', 'user1@yandex1.ru', 'password1');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO users (name, email, password)
VALUES ('Partner1', 'partner@yandex.ru', 'partner1');

INSERT INTO users (name, email, password)
VALUES ('Partner2', 'partner1@yandex.ru', 'partner2');

INSERT INTO users (name, email, password)
VALUES ('Partner3', 'partner2@yandex.ru', 'partner3');

INSERT INTO users (name, email, password)
VALUES ('Representative', 'representative@gmail.com', 'representative');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_ADMIN', 100002),
  ('ROLE_PARTNER', 100003),
  ('ROLE_PARTNER', 100004),
  ('ROLE_PARTNER', 100005),
  ('ROLE_REPRESENTATIVE', 100006);

insert into products (name, price, description, partnerid) values
('ferrari1', 10000000, 'cars', 100004),
('носки', 9900, 'носки обыкновенные', 100004),
('BMW', 1230000,'машина', 100004),
('BMW1234', 123,'мана', 100004),
('gtf45', 1000, 'dtf54', 100005),
('ferrari4', 10000000, 'cars', 100005),
('ferrari3', 10000000,'cars', 100005);

insert into credit_application (userid, fio, date_birth,
date_time_create, phone_number, an_initial_fee, status_of_application_parner, status_of_application_representative) values
(100000,'Вася Громов','2015-05-31', '2010-07-11 15:04:00', '89104567896', 10000, null , null),
(100000,'Вася Громов','1975-06-01', '2010-07-11 15:04:00', '89112312316', 10012, null , null),
(100001,'Вася1 Громов1','2000-06-01', '2010-01-11 15:04:00', '89112312326', 10012, null , null);

insert into credit_application_list_product(cred_app_id, productl_id) values
(100014, 100007),
(100014, 100008),
(100015, 100011);
