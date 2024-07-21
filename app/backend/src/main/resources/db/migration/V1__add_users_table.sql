CREATE TABLE "user" (
    id serial primary key,
    username varchar(50) not null,
    password varchar(255) not null,
    email varchar(100) not null,
    created_at timestamp default current_timestamp,
    avatar_url varchar(512) default null
);
