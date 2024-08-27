CREATE TABLE account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(255),
    account_type VARCHAR(255),
    initial_amount INT,
    status BOOLEAN,
    client_id BIGINT
);