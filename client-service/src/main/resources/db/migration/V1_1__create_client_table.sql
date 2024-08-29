CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    password VARCHAR(255),
    status boolean,
    person_id INT,
    CONSTRAINT fk_person
        FOREIGN KEY(person_id) 
        REFERENCES person(id)
);