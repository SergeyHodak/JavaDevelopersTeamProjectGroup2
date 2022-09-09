ALTER TABLE note
DROP CONSTRAINT note_pkey;

ALTER TABLE note
DROP COLUMN id;

ALTER TABLE note
ADD id uuid DEFAULT uuid_generate_v4() PRIMARY KEY;

UPDATE users
SET password='$2a$10$sJhCLqPnUmaGpZjLWD9scOMbIN.M1UPoK.rXZ/7n7wIyx68L56wvy'
WHERE email='admin@ukr.net';

CREATE TABLE IF NOT EXISTS user_note (
    user_id uuid NOT NULL,
    note_id uuid NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY(note_id) REFERENCES note(id) ON DELETE CASCADE
);