CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    status boolean DEFAULT false,
    CONSTRAINT fk_person
        FOREIGN KEY(id) 
        REFERENCES person(id)
);