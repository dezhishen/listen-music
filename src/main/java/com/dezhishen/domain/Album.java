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
    private int id;
    private String name;
    private List<Artist> artists;
    private Date publishTime;
    private int size;
    private int copyrightId;
    private int status;
    private int picId;
    private int mark;
}
