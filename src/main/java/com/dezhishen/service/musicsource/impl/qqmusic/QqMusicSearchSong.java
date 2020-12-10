package com.dezhishen.service.musicsource.impl.qqmusic;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 搜索接口返回的歌曲对象
 */
@Setter
@Getter
public class QqMusicSearchSong {
    private String songid;
    private String songmid;
    private String songname;
    private List<QqMusicArtist> singers;
}
