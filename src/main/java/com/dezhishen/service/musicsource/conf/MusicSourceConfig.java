package com.dezhishen.service.musicsource.conf;


import com.dezhishen.domain.MusicSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 音乐资源配置类
 *
 * @author dezhishen
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "music-server")
@Configuration
public class MusicSourceConfig {
    private Map<String, MusicSource> sources = new HashMap<>();

    public MusicSource getSource(String key) {
        return sources.get(key);
    }
}
