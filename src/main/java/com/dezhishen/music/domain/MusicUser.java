package com.dezhishen.music.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 音乐服务中的用户
 *
 * @author dezhishen
 */
@Getter
@Setter
public class MusicUser implements Serializable {
    private String id;
    private String name;
    private String headImageUrl;
}
