CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    email varchar(255) UNIQUE,
    first_name varchar(255),
    last_name varchar(255),
    password varchar(255)
);

CREATE TABLE IF NOT EXISTS note (
    id UUID PRIMARY KEY,
    name VARCHAR(250),
    content VARCHAR(10000),
    access_type VARCHAR(20),
    user_id UUID REFERENCES users(id)
);