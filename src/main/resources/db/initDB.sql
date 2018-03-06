DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS credit_application;
DROP SEQUENCE IF EXISTS global_seq CASCADE;
DROP SEQUENCE IF EXISTS product_seq CASCADE;
DROP SEQUENCE IF EXISTS creditapplication_seq CASCADE;

create sequence global_seq start 100000;
create sequence product_seq start 100000;
create sequence creditapplication_seq start 100000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR NOT NULL,
  email      VARCHAR NOT NULL,
  password   VARCHAR NOT NULL,
  registered TIMESTAMP DEFAULT now(),
  enabled    BOOL DEFAULT TRUE
);

CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE products
(   id        INTEGER PRIMARY KEY DEFAULT nextval('product_seq'),
    name      VARCHAR not null,
    price     INTEGER not null,
    partnerId INTEGER not null
);

CREATE TABLE credit_application
(   id                                      INTEGER PRIMARY KEY DEFAULT nextval('creditapplication_seq'),
    name                                    VARCHAR not null,
    userid                                  INTEGER not null,
    fio                                     VARCHAR not null,
    date_birth                              TIMESTAMP NOT NULL,
    date_time_create                        TIMESTAMP NOT NULL,
    phone_number                            VARCHAR not null,
    an_initial_fee                          INTEGER not null,
    status_of_application_parner            BOOLEAN,
    status_of_application_representative    BOOLEAN
);

/*
CREATE UNIQUE INDEX products_unique_user_datetime_idx ON products(user_id, date_time)*/
