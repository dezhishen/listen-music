package com.dezhishen.music.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 艺术家
 *
 * @author dezhishen
 */
@Getter
@Setter
public class Artist implements Serializable {
    private String id;
    private String name;
    private String picUrl;
}
