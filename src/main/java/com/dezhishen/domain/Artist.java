package com.dezhishen.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 艺术家
 *
 * @author dezhishen
 */
@Getter
@Setter
public class Artist {
    private String id;
    private String name;
    private String picUrl;
    private String img1v1Url;
    private long img1v1;
    private String trans;
}
