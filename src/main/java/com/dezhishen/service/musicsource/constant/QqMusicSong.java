package com.dezhishen.service.musicsource.constant;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author dezhishen
 */
@Getter
@Setter
public class QqMusicSong {
    @Getter
    @Setter
    public static class Artist {
        private String id;
        private String name;
        private String mid;
    }

    @Setter
    @Getter
    public static class Album {
        private String id;
        private String mid;
        private String name;
    }

    private String id;
    private String mid;
    private String title;
    private String name;
    private String url;
    private String lyric;
    private List<Artist> singers;
    private Album album;
}
