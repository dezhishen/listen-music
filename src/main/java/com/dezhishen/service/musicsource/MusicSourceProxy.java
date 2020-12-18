package com.dezhishen.service.musicsource;

import com.dezhishen.base.RespCode;
import com.dezhishen.domain.MusicUser;
import com.dezhishen.domain.PlayList;
import com.dezhishen.domain.Song;
import com.dezhishen.exception.MusicException;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.dezhishen.constant.CacheKey.MUSIC_URL;

/**
 * 音乐服务对外入口
 *
 * @author dezhishen
 */
@Slf4j
@Service
public class MusicSourceProxy {

    @Autowired
    private RedisTemplate redisTemplate;
    private Map<String, AbstractMusicSourceTemplate> _templateMaps = new HashMap<>();

    public void addTemplate(AbstractMusicSourceTemplate abstractMusicSourceTemplate) {
        _templateMaps.put(abstractMusicSourceTemplate.getSource(), abstractMusicSourceTemplate);
    }

    /**
     * 获取服务的实现模板
     *
     * @param source
     * @return
     */
    protected AbstractMusicSourceTemplate getTemplate(String source) {
        if (_templateMaps.containsKey(source)) {
            if (_templateMaps.get(source).getSourceConfig().getEnabled()) {
                return _templateMaps.get(source);
            }
            throw new MusicException(RespCode.EXCEPTION, "名为[%s]的音乐服务未启用,请检查配置或输入参数", source);
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
        String cacheKey = String.format(MUSIC_URL, source, id);
        String url = (String) redisTemplate.opsForValue().get(cacheKey);
        if (StringUtils.isEmpty(url)) {
            url = getTemplate(source).getSongUrlById(id);
            redisTemplate.opsForValue().set(cacheKey, url, 60, TimeUnit.SECONDS);
        }
        return url;
    }

    public PageInfo<Song> searchSong(String q, String source, Integer pageNum, Integer pageSize) {
        return getTemplate(source).searchSong(q, pageNum, pageSize);
    }

    public PageInfo<MusicUser> searchMusicUser(String q, String source, Integer pageNum, Integer pageSize) {
        return getTemplate(source).searchMusicUser(q, source, pageNum, pageSize);
    }

    public PageInfo<PlayList> searchPlayList(String q, String source, Integer pageNum, Integer pageSize) {
        return getTemplate(source).searchPlayList(q, source, pageNum, pageSize);
    }
}
