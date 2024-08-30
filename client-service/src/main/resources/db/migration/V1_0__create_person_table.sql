CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(255),
    age INT,
    identification VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(255)
);