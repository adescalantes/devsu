CREATE SCHEMA bank;

CREATE TABLE bank.client (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    age INT,
    identification VARCHAR(255) NOT NULL UNIQUE,
    address VARCHAR(255),
    phone_number VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL
);

ALTER SEQUENCE client_id_seq RESTART WITH 100000;

CREATE TABLE bank.account (
    account_number SERIAL PRIMARY KEY,
    client_id INT NOT NULL,
    account_type VARCHAR(50) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    status BOOLEAN NOT NULL,
    FOREIGN KEY (client_id) REFERENCES bank.client(id)
);

ALTER SEQUENCE account_account_number_seq RESTART WITH 100000;

CREATE TABLE bank.movement (
    id SERIAL PRIMARY KEY,
    account_id INT NOT NULL,
    create_date TIMESTAMP WITH TIME ZONE NOT NULL,
    movement_type VARCHAR(50) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    initial_balance DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (account_id) REFERENCES bank.account(account_number)
);
