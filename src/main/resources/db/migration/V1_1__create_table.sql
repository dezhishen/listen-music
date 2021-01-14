create table system_user(
    id varchar(32) not null,
    name varchar(40) not null
);

create table biscuit(
    id varchar(32) not null,
    user_id varchar(32) not null
);

create table song(
    id varchar(100) not null,
    name varchar(100) not null,
    source varchar(100) not null,
    artists_name varchar(40) null ,
    lyric text null
);

create table play_list (
    id varchar(32) not null,
    user_id varchar(32) not null,
    name varchar(40) not null,
    cover text ,
    description varchar(255)  null
);

create table play_list_song(
    song_id varchar(32) not null,
    source varchar(40) not null,
    play_list_id varchar(32) not null
);

create table house_user(
    house_id varchar(32) not null,
    user_id varchar(32) not null
);