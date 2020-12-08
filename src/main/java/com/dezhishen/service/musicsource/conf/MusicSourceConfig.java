package com.dezhishen.service.musicsource.conf;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 音乐资源配置类
 *
 * @author dezhishen
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "music-source")
public class MusicSourceConfig {
    private Map<String, MusicSourceProperties> properties;
}
