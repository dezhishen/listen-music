create table play_list_song (
	id varchar(32) not null comment '主键',
    play_list_id varchar(32) not null comment '歌曲列表Id',
    song_source varchar(40) not null comment '歌曲来源',
    song_id varchar(40) not null comment '歌曲id',
    primary key(id)
) comment '播放列表歌曲';

create table play_list(
    id varchar(32) not null comment '主键',
    user_id varchar(32) not null comment '用户id',
    name varchar(40) not null comment '名称',
    cover varchar(600) comment '封面',
    description varchar(200) comment '描述',
    tags varchar(200) comment '标签'
)comment '播放列表';