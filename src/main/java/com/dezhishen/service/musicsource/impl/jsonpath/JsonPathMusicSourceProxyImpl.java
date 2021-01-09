package com.dezhishen.service.musicsource.impl.jsonpath;

import com.dezhishen.domain.MusicUser;
import com.dezhishen.domain.PlayList;
import com.dezhishen.domain.Song;
import com.dezhishen.exception.MusicException;
import com.dezhishen.service.musicsource.IMusicSourceProxy;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
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
@Primary
public class JsonPathMusicSourceProxyImpl implements IMusicSourceProxy {

    @Resource(name = "MusicSourceTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private MusicServerConfig musicServerConfig;

    /**
     * 根据id获取音乐信息
     *
     * @param source
     * @param id
     * @return
     */
    @Override
    public Song getSongById(String source, String id) {
        Map<String, Object> uriVariables = new HashMap<>(2);
        uriVariables.put("id", id);
        MusicServerProperty property = musicServerConfig.getProperty(source);
        MusicServerProperty.Uri uri = property.getUri("getSong");
        String resp = request(property.getBaseUri() + "/" + uri.getPath(), uriVariables, uri.getMethod());
        return null;
    }

    private String request(String url, Map<String, ?> uriVariables, HttpMethod httpMethod) {
        if (HttpMethod.GET.equals(httpMethod)) {
            return restTemplate.getForObject(url, String.class, uriVariables);
        }
        if (HttpMethod.PATCH.equals(httpMethod)) {
            return restTemplate.patchForObject(url, uriVariables, String.class, uriVariables);
        }
        if (HttpMethod.POST.equals(httpMethod)) {
            return restTemplate.postForObject(url, uriVariables, String.class, uriVariables);
        }
        if (HttpMethod.PUT.equals(httpMethod)) {
            restTemplate.put(url, uriVariables, uriVariables);
            return null;
        }
        if (HttpMethod.DELETE.equals(httpMethod)) {
            restTemplate.delete(url, uriVariables);
            return null;
        }
        throw new MusicException("不支持的类型[%s]", httpMethod.name());
    }

    @Override
    public String getSongUrlById(String source, String id) {
        return null;
    }

    @Override
    public PageInfo<Song> searchSong(String q, String source, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<MusicUser> searchMusicUser(String q, String source, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<PlayList> searchPlayList(String q, String source, Integer pageNum, Integer pageSize) {
        return null;
    }
}
