DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM products;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO users (name, email, password)
VALUES ('Partner', 'partner@yandex.ru', 'partner');

INSERT INTO users (name, email, password)
VALUES ('Partner1', 'partner1@yandex.ru', 'partner1');

INSERT INTO users (name, email, password)
VALUES ('Partner2', 'partner2@yandex.ru', 'partner2');

INSERT INTO users (name, email, password)
VALUES ('Representative', 'representative@gmail.com', 'representative');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_PARTNER', 100002),
  ('ROLE_PARTNER', 100003),
  ('ROLE_PARTNER', 100004),
  ('ROLE_REPRESENTATIVE', 100005);

insert into products (name, price, partnerid) values
('ferrari1', 10000000, 100003),
('носки', 9900, 100000),
('BMW', 1230000, 100001),
('gtf45', 1000, 100000),
('ferrari4', 10000000, 100000),
('ferrari3', 10000000, 100004);

insert into credit_application (name, userid, fio, date_birth,
date_time_create, phone_number, an_initial_fee, status_of_application_parner, status_of_application_representative) values
('Анкета 1', 100000,'Вася Громов','2015-05-31 10:00:00', '2010-07-11 15:04:00', '89104567896', 10000, null , null),
('Анкета 2', 100000,'Вася Громов','1975-06-01 10:12:00', '2010-07-11 15:04:00', '89112312316', 10012, null , null),
('Анкета 3', 100000,'Вася Громов','2015-01-21 10:00:00', '2010-07-11 15:04:00', '89123467896', 10000, null , null),
('Анкета 4', 100000,'Вася Громов','1915-11-11 10:34:00', '2010-07-11 15:04:00', '89104252396', 10000, null , null);
