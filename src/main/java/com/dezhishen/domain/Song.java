package com.dezhishen.domain;

import com.dezhishen.service.musicsource.impl.neteasecloud.NeteaseCloudSong;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 音乐对象
 *
 * @author dezhishen
 */
@Setter
@Getter
public class Song {
    private String source;
    private String id;
    private String name;
    private int position;
    private List<String> alias;
    private int status;
    private int fee;
    private int copyrightId;
    private String disc;
    private int no;
    private List<NeteaseCloudSong.Artist> artists;
    private NeteaseCloudSong.Album album;
    private String url;
    private Long lastUpdateTimestamp;

    public void setId(Object id) {
        if (id == null) {
            this.id = null;
        } else {
            this.id = id.toString();
        }
    }
}
