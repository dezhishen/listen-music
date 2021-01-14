package com.dezhishen.music.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 专辑
 *
 * @author dezhishen
 */
@Getter
@Setter
public class Album implements Serializable {
    private String name;
    private String id;
    private List<Artist> artists;
    private Date publishTime;
    private Long size;
    private Long copyrightId;
    private Integer status;
    private Long picId;
    private Long mark;
}
