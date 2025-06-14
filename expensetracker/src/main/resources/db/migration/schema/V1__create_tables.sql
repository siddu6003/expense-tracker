-- V1__create_tables.sql

-- Create Users table
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Primary key, auto-incrementing
    name VARCHAR(100),                     -- User name
    username VARCHAR(100) UNIQUE,          -- Unique username
    password VARCHAR(255),                 -- User password (hashed)
    createDate DATE,                       -- Account creation date
    updatedDate DATE                       -- Last updated date
);

-- Create Category table
CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Primary key, auto-incrementing
    name VARCHAR(100) NOT NULL,            -- Category name
    type VARCHAR(50) NOT NULL              -- Type of the category (e.g., 'income', 'expense')
);

-- Create CreditTransactions table
CREATE TABLE credit_transactions (
    creditId BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Primary key, auto-incrementing
    user_id BIGINT,                              -- Foreign key to users
    description BLOB,                            -- Description of the credit transaction (binary)
    amount DECIMAL(10, 2),                       -- Credit amount (with two decimal places)
    categoryId BIGINT,                           -- Foreign key to category
    FOREIGN KEY (user_id) REFERENCES users(id), -- Foreign key constraint on user_id
    FOREIGN KEY (categoryId) REFERENCES category(id)  -- Foreign key constraint on categoryId
);

-- Create DebitTransactions table
CREATE TABLE debit_transactions (
    debitId BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Primary key, auto-incrementing
    user_id BIGINT,                             -- Foreign key to users
    description BLOB,                           -- Description of the debit transaction (binary)
    amount DECIMAL(10, 2),                       -- Debit amount (with two decimal places)
    categoryId BIGINT,                          -- Foreign key to category
    FOREIGN KEY (user_id) REFERENCES users(id),    -- Foreign key constraint on user_id
    FOREIGN KEY (categoryId) REFERENCES category(id)  -- Foreign key constraint on categoryId
);
