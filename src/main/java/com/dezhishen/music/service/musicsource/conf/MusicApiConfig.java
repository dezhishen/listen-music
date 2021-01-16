package com.dezhishen.music.service.musicsource.conf;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicApiConfig {
    private MusicApiGetSongConfig song;
    private MusicApiGetSongUrlConfig songUrl;
    private MusicApiSearchSongConfig searchSong;
    private MusicApiGetSongLyrConfig songLyric;
    private MusicApiGetPlayListConfig playList;
}
