create table if not exist post (
    id serial primary key,
    name varchar(255),
    text text,
    link varchar(255),
    created timestamp
    );