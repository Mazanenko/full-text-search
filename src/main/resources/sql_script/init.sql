drop table if exists books cascade;
drop table if exists authors cascade;

CREATE TABLE authors (
    id          bigserial           primary key,
    created_at  timestamp           NOT NULL,
    modified_at timestamp           NOT NULL,
    first_name  varchar(255)        NOT NULL,
    middle_name varchar(255)        NULL,
    last_name   varchar(255)        NOT NULL
);

CREATE TABLE books (
    id          bigserial           primary key,
    created_at  timestamp           NOT NULL,
    modified_at timestamp           NOT NULL,
    name        varchar(255)        NOT NULL,
    genre       varchar(255)        NOT NULL,
    description text                NOT NULL,
    price       double precision    NOT NULL,
    author_id   bigint,
    CONSTRAINT books_uk_author_name UNIQUE (author_id, name),
    CONSTRAINT books_fk_author FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE SET NULL
);
