package com.dezhishen.music.domain;

import com.dezhishen.music.base.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author dezhishen
 */
@Setter
@Getter
public class MusicSource extends BaseDomain {
    /**
     * 是否启用
     */
    private Boolean enabled;
    /**
     * 名称
     */
    private String label;
    /**
     * 地址
     */
    private String uri;
    /**
     * 描述
     */
    private String description;
    /**
     * 其他属性
     */
    private Map<String, String> properties;
}
