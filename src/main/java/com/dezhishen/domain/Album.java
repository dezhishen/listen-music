package com.dezhishen.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 专辑
 *
 * @author dezhishen
 */
@Getter
@Setter
public class Album {
    private String name;
    private long id;
    private List<Artist> artists;
    private Date publishTime;
    private long size;
    private long copyrightId;
    private int status;
    private long picId;
    private long mark;
}
