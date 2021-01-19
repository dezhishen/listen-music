package com.dezhishen.music.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 音乐对象
 *
 * @author dezhishen
 */
@Setter
@Getter
public class Song implements Serializable {
    private String source;
    private String id;
    private String name;
    private String cover;
    @TableField(exist = false)
    private Integer position;
    @TableField(exist = false)
    private List<String> alias;
    @TableField(exist = false)
    private Integer status;
    @TableField(exist = false)
    private Integer fee;
    @TableField(exist = false)
    private Integer copyrightId;
    @TableField(exist = false)
    private String disc;
    @TableField(exist = false)
    private Integer no;
    @TableField(exist = false)
    private List<Artist> artists;
    @TableField(exist = false)
    private Album album;
    @TableField(exist = false)
    private String url;
    @TableField(exist = false)
    private Long lastUpdateTimestamp;
    private String artistsName;
    private String lyric;
    /**
     * 是否免费
     */
    @TableField(exist = false)
    private Boolean free;

    @TableField(exist = false)
    private Map<String, String> properties;

    public void setId(Object id) {
        if (id == null) {
            this.id = null;
        } else {
            this.id = id.toString();
        }
    }
}
