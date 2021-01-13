package com.dezhishen.service.musicsource.conf;

import com.dezhishen.exception.MusicException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "music-server")
@Configuration
public class MusicServerConfig {
    private Map<String, MusicServerProperty> services;

    public MusicServerProperty getProperty(String source) {
        if (services == null || services.isEmpty()) {
            throw new MusicException("未找到任何音乐服务配置");
        }
        MusicServerProperty result = services.get(source);
        if (result == null) {
            throw new MusicException("未找到指定的音乐服务[%s]", source);
        }
        if (!result.isEnabled()) {
            throw new MusicException("指定的音乐服务[%s]未启动", source);
        }
        return result;
    }
}
