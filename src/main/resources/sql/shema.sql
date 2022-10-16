CREATE SCHEMA IF NOT EXISTS cinema;

CREATE TABLE IF NOT EXISTS cinema.user
(
    id           int primary key generated always as identity,
    first_name    varchar(60),
    last_name     varchar(60),
    email        varchar(80),
    phone_number varchar(20),
    password     varchar(60)
);