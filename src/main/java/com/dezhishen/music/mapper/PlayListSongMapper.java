package com.dezhishen.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dezhishen.music.domain.PlayListSong;
import com.dezhishen.music.domain.Song;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlayListSongMapper extends BaseMapper<PlayListSong> {
    List<Song> selectSongByPlayListId(@Param("playListId") String playListId);
}
