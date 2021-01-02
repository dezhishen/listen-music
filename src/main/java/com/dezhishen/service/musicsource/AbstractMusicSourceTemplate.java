package com.dezhishen.service.musicsource;

import com.dezhishen.domain.MusicSource;
import com.dezhishen.domain.MusicUser;
import com.dezhishen.domain.PlayList;
import com.dezhishen.domain.Song;
import com.dezhishen.service.MusicSourceService;
import com.github.pagehelper.PageInfo;
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
    private MusicSourceService musicSourceService;

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


    protected MusicSource getSourceConfig() {
        return musicSourceService.get(getSource());
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

    /**
     * 根据id获取播放地址
     *
     * @param id
     * @return
     */
    public abstract String getSongUrlById(String id);

    /**
     * 搜索音乐
     *
     * @param q
     * @param pageNum
     * @param pageSize
     * @return
     */
    public abstract PageInfo<Song> searchSong(String q, Integer pageNum, Integer pageSize);

    /**
     * 搜索用户
     *
     * @param q
     * @param source
     * @param pageNum
     * @param pageSize
     * @return
     */
    public abstract PageInfo<MusicUser> searchMusicUser(String q, String source, Integer pageNum, Integer pageSize);

    /**
     * 搜索歌单
     *
     * @param q
     * @param source
     * @param pageNum
     * @param pageSize
     * @return
     */
    public abstract PageInfo<PlayList> searchPlayList(String q, String source, Integer pageNum, Integer pageSize);

    /**
     * 登录
     *
     * @param phone
     * @param password
     * @return
     */
    public abstract Boolean loginByPhone(String phone, String password);
}
