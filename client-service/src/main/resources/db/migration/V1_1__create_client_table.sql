CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    status boolean DEFAULT false,
    person_id INT NOT NULL,
    CONSTRAINT fk_person
        FOREIGN KEY(person_id) 
        REFERENCES person(id)
);