package com.dezhishen.domain;

import com.dezhishen.base.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
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
    private List<String> tags;
}
