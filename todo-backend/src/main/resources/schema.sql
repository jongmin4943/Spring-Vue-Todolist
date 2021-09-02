
Drop TABLE IF EXISTS user;
create table user
(
    username     varchar(50)                          not null
        primary key,
    password     varchar(255)                         not null,
    created_date datetime default current_timestamp() null,
    updated_date datetime default current_timestamp() null
);

Drop TABLE IF EXISTS user_role;
create table user_role
(
    username varchar(50) not null,
    role     varchar(50) not null,
    constraint fk_user_role
        foreign key (username) references user (username)
);

DROP TABLE IF EXISTS todo;
create table todo
(
    tno          bigint auto_increment
        primary key,
    title        varchar(255)                         not null,
    username     varchar(50)                          not null,
    done         bit      default b'0'                not null,
    position        bigint,
    created_date datetime default current_timestamp() null,
    updated_date datetime default current_timestamp() null,
    constraint fk_todo_user_
        foreign key (username) references user (username)
);