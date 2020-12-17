package com.dezhishen.service.musicsource.conf;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author dezhishen
 */
@Getter
@Setter
public class MusicSourceProperties {
    /**
     * 地址
     */
    private String uri;

    /**
     * 其他属性
     */
    private Map<String, String> properties;
    /**
     * 是否启用
     */
    private Boolean enabled;
}
