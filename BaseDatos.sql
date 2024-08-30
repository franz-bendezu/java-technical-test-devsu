-- Nota: CON SPRING_FLYWAY_ENABLED=true (actualmente en false)
--- en docker-compose.yml se ejecutaran las migraciones de las bases de datos 
--- al iniciar los contenedores de los microservicios

--- Microservicio Cuenta-Movimiento (account-service)
-- Base de datos MySQL
CREATE TABLE account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(255),
    account_type VARCHAR(255),
    initial_amount INT,
    status BOOLEAN,
    client_id BIGINT
);

CREATE TABLE transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transaction_type VARCHAR(255),
    amount INT,
    balance INT,
    account_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_account
        FOREIGN KEY(account_id) 
        REFERENCES account(id)
);



--- Microservicio Cliente-Persona (client-service)
-- Base de datos PostgreSQL

CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(255),
    age INT,
    identification VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(255)
);

CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    status boolean DEFAULT false,
    CONSTRAINT fk_person
        FOREIGN KEY(id) 
        REFERENCES person(id)
);