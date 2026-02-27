
CREATE TABLE users(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY ,
    user_name VARCHAR(30) NOT NULL ,
    number_phone VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE tables(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    number_table VARCHAR(5) NOT NULL ,
    capacity INTEGER NOT NULL ,
    location VARCHAR(15) NOT NULL
);
CREATE TABLE reservations(
                             id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                             user_id BIGINT NOT NULL ,
                             table_id BIGINT NOT NULL ,
                             start_reservation TIMESTAMP WITH TIME ZONE NOT NULL ,
                             end_reservation TIMESTAMP WITH TIME ZONE NOT NULL ,
                             comment TEXT,
                             event VARCHAR(10) NOT NULL,
                             status VARCHAR(10) NOT NULL,
                             created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

                             CONSTRAINT fk_reservation_user FOREIGN KEY(user_id) REFERENCES users(id),
                             CONSTRAINT fk_reservation_table FOREIGN KEY(table_id) REFERENCES tables(id)
);