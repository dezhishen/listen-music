package com.dezhishen.service.musicsource.impl.qqmusic;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author dezhishen
 */
@Getter
@Setter
public class QqMusicSong {
    @Setter
    @Getter
    public static class Album {
        private String id;
        private String mid;
        private String name;

        public Album() {
        }
    }

    private String id;
    private String mid;
    private String title;
    private String name;
    private String url;
    private String lyric;
    private List<QqMusicArtist> singer;
    private Album album;

    public QqMusicSong() {
    }
}
