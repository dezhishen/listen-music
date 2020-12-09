package com.dezhishen.service.musicsource;

import com.dezhishen.base.RespCode;
import com.dezhishen.domain.MusicUser;
import com.dezhishen.domain.PlayList;
import com.dezhishen.domain.Song;
import com.dezhishen.exception.MusicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 音乐服务对外入口
 *
 * @author dezhishen
 */
@Service
@Slf4j
public class MusicSourceProxy {
    /**
     * 只作为注入容器,不直接使用,请直接使用 _templateMaps
     */
    @Autowired
    private List<AbstractMusicSourceTemplate> templates;

    private Map<String, AbstractMusicSourceTemplate> _templateMaps = new HashMap<>();


    @PostConstruct
    public void init() {
        log.info("开始初始化MusicServer服务列表");
        if (templates == null || templates.isEmpty()) {
            log.error("可用列表为空");
            throw new MusicException(RespCode.EXCEPTION, "未找到任何音乐服务,请至少继承一个[com.dezhishen.service.musicsource.AbstractMusicSourceTemplate],且注册到spring的bean工厂中");
        }
        for (AbstractMusicSourceTemplate template : templates) {
            log.info("注册音乐服务[{}],uri:[{}]", template.getSource(), template.getSourceConfig().getUri());
            _templateMaps.put(template.getSource(), template);
        }
    }

    /**
     * 获取服务的实现模板
     *
     * @param source
     * @return
     */
    protected AbstractMusicSourceTemplate getTemplate(String source) {
        if (_templateMaps.containsKey(source)) {
            return _templateMaps.get(source);
        }
        throw new MusicException(RespCode.EXCEPTION, "未找到名为[%s]的音乐服务,请检查配置或输入参数", source);
    }

    /**
     * 根据id获取音乐信息
     *
     * @param source
     * @param id
     * @return
     */
    public Song getSongById(String source, String id) {
        Song result = getTemplate(source).getSongById(id);
        if (result == null) {
            return null;
        }
        result.setSource(source);
        return result;
    }

    public String getSongUrlById(String source, String id) {
        return getTemplate(source).getSongUrlById(id);
    }

    public Page<Song> searchSong(String q, String source, Integer pageNum, Integer pageSize) {
        return getTemplate(source).searchSong(q, pageNum, pageSize);
    }

    public Page<MusicUser> searchMusicUser(String q, String source, Integer pageNum, Integer pageSize) {
        return getTemplate(source).searchMusicUser(q, source, pageNum, pageSize);
    }

    public Page<PlayList> searchPlayList(String q, String source, Integer pageNum, Integer pageSize) {
        return getTemplate(source).searchPlayList(q, source, pageNum, pageSize);
    }
}
