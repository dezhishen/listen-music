package com.dezhishen.service.musicsource;

import com.dezhishen.domain.Song;
import com.dezhishen.service.musicsource.conf.MusicSourceConfig;
import com.dezhishen.service.musicsource.conf.MusicSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 音乐资源来源的模板
 *
 * @author dezhishen
 */
public abstract class AbstractMusicSourceTemplate {
    @Autowired
    protected MusicSourceConfig config;
    @Resource(name = "MusicSourceTemplate")
    protected RestTemplate restTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    protected String getUri() {
        return getSourceConfig().getUri();
    }

    @Bean(name = "MusicSourceTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    protected MusicSourceProperties getSourceConfig() {
        return config.getSources().get(getSource());
    }

    /**
     * 当前来源标识
     *
     * @return
     */
    public abstract String getSource();

    /**
     * 根据id获取音乐信息
     *
     * @param id
     * @return
     */
    public abstract Song getSongById(String id);

    public abstract String getSongUrlById(String id);
}
