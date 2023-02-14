CREATE TABLE bookings (
    id serial NOT NULL,
    user_id VARCHAR NOT NULL,
    from_date TIMESTAMP NOT NULL,
    to_date TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);
