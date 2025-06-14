-- Drop existing tables
DROP TABLE IF EXISTS credit_transactions;
DROP TABLE IF EXISTS debit_transactions;

-- Create unified Transactions table
CREATE TABLE transactions (
    transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Primary key, auto-incrementing
    user_id BIGINT NOT NULL,                           -- Foreign key to users
    description BLOB,                                  -- Transaction description (binary)
    amount DECIMAL(10, 2) NOT NULL,                    -- Transaction amount
    category_id BIGINT NOT NULL,                       -- Foreign key to category
    type VARCHAR(10) NOT NULL,                         -- 'CREDIT' or 'DEBIT'
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,    -- Timestamp for creation
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Auto-update
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);
