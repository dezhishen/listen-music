package com.dezhishen.service.musicsource.impl.neteasecloud;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
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
        private String id;
        private String name;
        private String picUrl;
        private String img1v1Url;
        private long img1v1;
        private String trans;

        public Artist() {
        }
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
        private Long id;
        private List<Artist> artists;
        private Date publishTime;
        private Long size;
        private Long copyrightId;
        private Integer status;
        private Long picId;
        private Long mark;

        public Album() {

        }
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
    private List<Artist> ar;
    private Album album;

    public NeteaseCloudSong() {
    }
}
