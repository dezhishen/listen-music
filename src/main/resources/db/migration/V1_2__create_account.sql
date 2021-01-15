create table system_account(
    id varchar(32) not null,
    login_name varchar(40) not null,
    salt varchar(6) not null,
    password varchar(32) not null,
    user_id varchar(32) not null
);