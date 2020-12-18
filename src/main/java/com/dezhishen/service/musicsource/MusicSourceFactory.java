package com.dezhishen.service.musicsource;

import com.dezhishen.base.RespCode;
import com.dezhishen.domain.MusicSource;
import com.dezhishen.exception.MusicException;
import com.dezhishen.service.MusicSourceService;
import com.dezhishen.service.musicsource.conf.MusicSourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 音乐服务工厂
 *
 * @author dezhishen
 */
@Service
@Slf4j
public class MusicSourceFactory {
    /**
     * 只作为注入容器,不直接使用,请直接使用 _templateMaps
     */
    @Autowired
    private List<AbstractMusicSourceTemplate> templates;
    @Autowired
    private MusicSourceConfig musicSourceConfig;
    @Autowired
    private MusicSourceService musicSourceService;

    @PostConstruct
    public void buildProxy(@Autowired MusicSourceProxy proxy) {
        log.info("开始初始化MusicServer服务列表");
        if (templates == null || templates.isEmpty()) {
            log.error("可用列表为空");
            throw new MusicException(RespCode.EXCEPTION, "未找到任何音乐服务,请至少继承一个[com.dezhishen.service.musicsource.AbstractMusicSourceTemplate],且注册到spring的bean工厂中");
        }
        for (AbstractMusicSourceTemplate template : templates) {
            MusicSource t = musicSourceConfig.getSource(template.getSource());
            t.setId(template.getSource());
            log.info("注册音乐服务[{}],uri:[{}],是否启用:[{}]", t.getId(), t.getUri(), t.getEnabled());
            musicSourceService.save(t);
            proxy.addTemplate(template);
        }
    }
}
