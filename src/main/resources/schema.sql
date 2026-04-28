
CREATE TABLE users (
    user_id serial primary key ,
    user_name varchar(100) not null ,
    email varchar(100) not null  unique ,
    password varchar(100) not null ,
    token_version int null ,
    created_at timestamp
);

CREATE TABLE posts(
    post_id serial primary key ,
    title varchar(100) not null ,
    description varchar(255) not null ,
    image varchar(255),
    user_id int not null ,
    created_at timestamp,
    constraint fk_key foreign key (user_id)
        references users(user_id) on update cascade on delete cascade
);

drop table users,posts;
truncate table users restart identity cascade ;