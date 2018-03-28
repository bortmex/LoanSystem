DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS credit_application_list_product;
DROP TABLE IF EXISTS credit_application;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

create sequence global_seq start 100000;

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
(   id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name        VARCHAR not null,
    price       INTEGER not null,
    description VARCHAR not null,
    partnerid   INTEGER not null,
    FOREIGN KEY (partnerid) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE credit_application
(   id                                      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    userid                                  INTEGER not null,
    fio                                     VARCHAR not null,
    date_birth                              DATE NOT NULL,
    date_time_create                        TIMESTAMP NOT NULL,
    phone_number                            VARCHAR not null,
    an_initial_fee                          INTEGER not null,
    status_of_application_parner            BOOLEAN,
    status_of_application_representative    BOOLEAN,
    FOREIGN KEY (userid) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE credit_application_list_product
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  cred_app_id   INTEGER NOT NULL,
  productl_id   INTEGER NOT NULL,
  CONSTRAINT cred_app_productl_idx UNIQUE (cred_app_id, productl_id),
  FOREIGN KEY (cred_app_id) REFERENCES credit_application (id) ON DELETE CASCADE,
  FOREIGN KEY (productl_id) REFERENCES products (id) ON DELETE CASCADE
);
/*
CREATE UNIQUE INDEX products_unique_user_datetime_idx ON products(user_id, date_time)*/
