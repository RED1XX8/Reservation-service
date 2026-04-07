CREATE EXTENSION IF NOT EXISTS btree_gist;

CREATE TABLE tables(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    number_table VARCHAR(5) NOT NULL,
    capacity INTEGER NOT NULL,
    location VARCHAR(15) NOT NULL
);

CREATE TABLE reservations(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT NOT NULL,
    table_id BIGINT NOT NULL,
    start_reservation TIMESTAMP WITH TIME ZONE NOT NULL,
    end_reservation TIMESTAMP WITH TIME ZONE NOT NULL,
    comment TEXT,
    event VARCHAR(10) NOT NULL,
    status VARCHAR(10) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_resevation_table FOREIGN KEY (table_id) REFERENCES tables(id),
    CONSTRAINT no_overlapping_reservation
        exclude using gist(table_id with = , tstzrange(start_reservation , end_reservation) with && )

)
