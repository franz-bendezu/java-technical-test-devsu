CREATE TABLE transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transaction_type VARCHAR(255),
    amount INT,
    balance INT,
    account_id BIGINT,
    CONSTRAINT fk_account
        FOREIGN KEY(account_id) 
        REFERENCES account(id)
);