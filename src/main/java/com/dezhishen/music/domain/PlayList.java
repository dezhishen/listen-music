package com.dezhishen.music.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dezhishen.music.base.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 歌单
 *
 * @author dezhishen
 */
@Getter
@Setter
public class PlayList extends BaseDomain {
    private String id;
    private String userId;
    private String name;
    private String cover;
    private String description;
    @TableField(exist = false)
    private List<String> tags;
    @TableField(exist = false)
    private List<Song> songs;
}
