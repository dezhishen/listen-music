package com.dezhishen.domain;

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
    private Integer position;
    private List<String> alias;
    private Integer status;
    private Integer fee;
    private Integer copyrightId;
    private String disc;
    private Integer no;
    private List<Artist> artists;
    private Album album;
    private String url;
    private Long lastUpdateTimestamp;
    private String lyric;
    /**
     * 是否免费
     */
    private Boolean free;

    public void setId(Object id) {
        if (id == null) {
            this.id = null;
        } else {
            this.id = id.toString();
        }
    }
}
