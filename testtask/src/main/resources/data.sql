DROP TABLE IF EXISTS users;

CREATE TABLE users (
  `id` INT AUTO_INCREMENT  PRIMARY KEY,
  `username` VARCHAR(250) NOT NULL
);

INSERT INTO users (id, username) VALUES (1, 'test_user');

DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
  `id` INT AUTO_INCREMENT  PRIMARY KEY,
  `user_id` INT NOT NULL,
  `balance` DOUBLE NOT NULL,
  `version` INT
);

INSERT INTO accounts (id, user_id, balance, version) VALUES (1, 1, 120, 1);

DROP TABLE IF EXISTS transactions;

CREATE TABLE transactions (
  `id` VARCHAR(255) PRIMARY KEY,
  `user_id` INT NOT NULL,
  `type` VARCHAR(10) NOT NULL,
  `amount` DOUBLE NOT NULL,
  `effective_date` DATETIME
);

