package com.dezhishen.service.musicsource.impl.neteasecloud;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 网易云音乐 歌曲对象
 *
 * @author dezhishen
 */
@Getter
@Setter
public class NeteaseCloudSong {
    /**
     * 艺术家
     *
     * @author dezhishen
     */
    @Getter
    @Setter
    public static class Artist {
        private int id;
        private String name;
    }

    /**
     * 专辑
     *
     * @author dezhishen
     */
    @Getter
    @Setter
    public static class Album {
        private String name;
        private int id;
    }

    private int id;
    private String name;
    private int position;
    private List<String> alias;
    private int status;
    private int fee;
    private int copyrightId;
    private String disc;
    private int no;
    private List<Artist> artists;
    private Album album;

}
