package com.dezhishen.service.musicsource.impl;

import com.dezhishen.constant.CacheKey;
import com.dezhishen.domain.*;
import com.dezhishen.exception.MusicException;
import com.dezhishen.service.musicsource.IMusicResourceServiceProxy;
import com.dezhishen.service.musicsource.conf.*;
import com.dezhishen.storage.MusicSourceStorage;
import com.github.pagehelper.PageInfo;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 音乐服务对外入口
 *
 * @author dezhishen
 */
@Slf4j
@Service
@Primary
public class JsonPathMusicResourceServiceProxyImpl implements IMusicResourceServiceProxy {

    @Resource(name = "MusicSourceTemplate")
    private RestTemplate restTemplate;

    @Bean(name = "MusicSourceTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

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
        Song result = getSongWithoutLyrBySourceAndId(source, id);
        result.setLyric(getLyric(source, id));
        return result;
    }

    private Song getSongWithoutLyrBySourceAndId(String source, String id) {
        Cache cache = cacheManager.getCache(CacheKey.SONG);
        String key = CacheKey.getKeyBySourceAndId(source, id);
        Song result = cache.get(key, Song.class);
        if (result == null) {
            Map<String, Object> uriVariables = new HashMap<>(2);
            uriVariables.put("id", id);
            MusicServerProperty property = musicServerConfig.getProperty(source);
            MusicApiGetSongConfig config = property.getApi().getSong();
            String resp = request(
                    property.getBaseUri() + "/" + config.getUri(),
                    uriVariables,
                    config.getMethod()
            );
            Object songJson = read(resp, config.getRoot());
            result = new Song();
            result.setId(read(songJson, config.getId()));
            result.setName(read(songJson, config.getName()));
            JsonPathArtistsConfig artistsConfig = config.getArtists();
            List<Object> artistsJsonArray = read(songJson, artistsConfig.getRoot());
            if (artistsJsonArray != null && !artistsJsonArray.isEmpty()) {
                List<Artist> artists = new ArrayList<>();
                for (Object artistsJson : artistsJsonArray) {
                    Artist artist = new Artist();
                    artist.setId(read(artistsJson, artistsConfig.getId()).toString());
                    artist.setName(read(artistsJson, artistsConfig.getName()));
                    artists.add(artist);
                }
                result.setArtists(artists);
            }
            if (config.isProcessProperties()
                    && config.getPropertiesAlias() != null
                    && !config.getPropertiesAlias().isEmpty()
            ) {
                Map<String, String> properties = new HashMap<>();
                config.getPropertiesAlias().forEach((k, exp) -> {
                    String value = read(songJson, exp);
                    if (!StringUtils.isEmpty(value)) {
                        properties.put(k, value);
                    }
                });
                if (!properties.isEmpty()) {
                    result.setProperties(properties);
                }
            }
            result.setSource(source);
            cache.put(key, result);
        }
        return result;
    }

    @Override
    public String getSongUrlById(String source, String id) {
        Cache cache = cacheManager.getCache(CacheKey.MUSIC_URL);
        String key = CacheKey.getKeyBySourceAndId(source, id);
        String url = cache.get(key, String.class);
        if (StringUtils.isEmpty(url)) {
            Map<String, Object> uriVariables = new HashMap<>(2);
            uriVariables.put("id", id);
            MusicServerProperty property = musicServerConfig.getProperty(source);
            MusicApiGetSongUrlConfig config = property.getApi().getSongUrl();
            if (config.isUseProperties()) {
                Song song = getSongWithoutLyrBySourceAndId(source, id);
                if (song.getProperties() != null && !song.getProperties().isEmpty()) {
                    uriVariables.putAll(song.getProperties());
                }
            }
            String resp = request(
                    property.getBaseUri() + "/" + config.getUri(),
                    uriVariables,
                    config.getMethod()
            );
            Object root = read(resp, config.getRoot());
            url = read(root, config.getUrl());
            cache.put(key, url);
        }
        return url;
    }

    @Override
    public PageInfo<Song> searchSong(String q, String source, Integer pageNum, Integer pageSize) {
        Map<String, Object> uriVariables = new HashMap<>(8);
        uriVariables.put("q", q);
        uriVariables.put("pageNum", pageNum);
        uriVariables.put("pageSize", pageSize);
        uriVariables.put("offset", pageSize * (pageNum - 1));
        uriVariables.put("limit", pageSize);
        MusicServerProperty property = musicServerConfig.getProperty(source);
        MusicApiSearchSongConfig config = property.getApi().getSearchSong();
        String resp = request(
                property.getBaseUri() + "/" + config.getUri(),
                uriVariables,
                config.getMethod()
        );
        PageInfo<Song> result = new PageInfo<>();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        Object resultJson = read(resp, config.getRoot());
        Object total = read(resultJson, config.getTotal());
        if (total == null) {
            result.setTotal(0);
        } else {
            result.setTotal(Long.parseLong(total.toString()));
        }
        MusicApiSearchSongConfig.SongConfig listConfig = config.getList();
        List<Object> songJsonArray = read(resultJson, listConfig.getRoot());
        MusicApiSearchSongConfig.ArtistConfig artistConfig = listConfig.getArtists();
        if (songJsonArray != null && !songJsonArray.isEmpty()) {
            List<Song> list = new ArrayList<>();
            for (Object songJson : songJsonArray) {
                Song song = new Song();
                song.setId(read(songJson, listConfig.getId()).toString());
                song.setName(read(songJson, listConfig.getName()));
                List<Object> artistsJsonArray = read(songJson, artistConfig.getRoot());
                if (artistsJsonArray != null && !artistsJsonArray.isEmpty()) {
                    List<Artist> artists = new ArrayList<>();
                    for (Object artistsJson : artistsJsonArray) {
                        Artist artist = new Artist();
                        artist.setId(read(artistsJson, artistConfig.getId()).toString());
                        artist.setName(read(artistsJson, artistConfig.getName()));
                        artist.setPicUrl(read(artistsJson, artistConfig.getPicUrl()));
                        artists.add(artist);
                    }
                    song.setArtists(artists);
                }
                song.setSource(source);
                list.add(song);
            }
            result.setList(list);
        }
        return result;
    }

    @Override
    public PageInfo<MusicUser> searchMusicUser(String q, String source, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<PlayList> searchPlayList(String q, String source, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public String getLyric(String source, String id) {
        Map<String, Object> uriVariables = new HashMap<>(2);
        uriVariables.put("id", id);
        MusicServerProperty property = musicServerConfig.getProperty(source);
        MusicApiGetSongLyrConfig config = property.getApi().getSongLyric();
        if (config.isUseProperties()) {
            Song song = getSongWithoutLyrBySourceAndId(source, id);
            if (song.getProperties() != null && !song.getProperties().isEmpty()) {
                uriVariables.putAll(song.getProperties());
            }
        }
        String resp = request(
                property.getBaseUri() + "/" + config.getUri(),
                uriVariables,
                config.getMethod()
        );
        Object root = read(resp, config.getRoot());
        return read(root, config.getLyric());
    }

    @Autowired
    private MusicSourceStorage musicSourceStorage;

    @PostConstruct
    public void initSources() {
        if (musicServerConfig.getServices() == null || musicServerConfig.getServices().isEmpty()) {
            throw new MusicException("没有可用的音乐服务");
        }
        musicServerConfig.getServices().forEach((id, value) -> {
            MusicSource source = new MusicSource();
            source.setEnabled(value.isEnabled());
            source.setLabel(value.getLabel());
            source.setUri(value.getBaseUri());
            source.setId(id);
            musicSourceStorage.save(source);
        });
    }

    private String request(String url, Map<String, ?> uriVariables, HttpMethod httpMethod) {
        if (httpMethod == null) {
            httpMethod = HttpMethod.GET;
        }
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

    private <T> T read(Object source, String exp) {
        if (source == null) {
            return null;
        }
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        if (StringUtils.isEmpty(exp)) {
            return null;
        }
        if (source instanceof String) {
            return JsonPath.read((String) source, exp);
        }
        return JsonPath.read(source, exp);
    }
}
