CREATE TABLE users (
    id UUID PRIMARY KEY,
    email varchar(255) UNIQUE,
    first_name varchar(255),
    last_name varchar(255),
    password varchar(255)
);

CREATE TABLE note (
    id UUID PRIMARY KEY,
    name VARCHAR(250),
    content VARCHAR(10000),
    access_type VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS user_note (
    user_id UUID NOT NULL,
    note_id UUID NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY(note_id) REFERENCES note(id) ON DELETE CASCADE
);