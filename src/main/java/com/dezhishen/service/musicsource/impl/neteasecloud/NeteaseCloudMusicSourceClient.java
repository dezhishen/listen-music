package com.dezhishen.service.musicsource.impl.neteasecloud;

import com.alibaba.fastjson.JSONObject;
import com.dezhishen.domain.MusicUser;
import com.dezhishen.domain.PlayList;
import com.dezhishen.domain.Song;
import com.dezhishen.exception.MusicException;
import com.dezhishen.service.musicsource.AbstractMusicSourceTemplate;
import com.dezhishen.service.musicsource.constant.MusicSources;
import com.dezhishen.service.musicsource.util.CovertUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * NeteaseCloud 客户端
 *
 * @author dezhishen
 */
@Service
@Slf4j
public class NeteaseCloudMusicSourceClient extends AbstractMusicSourceTemplate {
    @Override
    public String getSource() {
        return MusicSources.NETEASE_CLOUD;
    }


    @Override
    public Song getSongById(String id) {
        NeteaseCloudMusics resp = restTemplate.getForObject(getUri() + "/song/detail/?ids=" + id, NeteaseCloudMusics.class);
        if (resp == null || resp.getSongs() == null || resp.getSongs().isEmpty()) {
            return null;
        }
        NeteaseCloudSong music = resp.getSongs().get(0);
        Song result = CovertUtil.neteaseCloudSong2Song(music);
        LrcResp lrcResp = restTemplate.getForObject(getUri() + "/lyric?id=" + id, LrcResp.class);
        if (lrcResp != null && lrcResp.getLrc() != null) {
            result.setLyric(lrcResp.getLrc().getLyric());
        }
        result.setSource(getSource());
        return result;
    }


    @Override
    public String getSongUrlById(String id) {
        NeteaseCloudUrlResp resp = restTemplate.getForObject(getUri() + "/song/url?id=" + id, NeteaseCloudUrlResp.class);
        if (resp == null || resp.getData() == null || resp.getData().isEmpty()) {
            return null;
        }
        return resp.getData().get(0).getUrl();
    }


    @Override
    public PageInfo<Song> searchSong(String q, Integer pageNum, Integer pageSize) {
        String uri = getUri() + "/search?keywords=" + q + "&offset=" + (pageNum - 1) * pageSize + "&limit=" + pageSize;
        log.info("uri {}", uri);
        String str = restTemplate.getForObject(uri, String.class);
        log.info("resp {}", str);
        PageInfo<Song> result = new PageInfo<>();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        SearchSongResp resp = JSONObject.parseObject(str, SearchSongResp.class);
        if (resp == null || resp.getResult() == null) {
            result.setTotal(0);
            return result;
        }
        List<Song> content = new ArrayList<>();
        if (resp.getResult().getSongs() != null) {
            for (NeteaseCloudSong song : resp.getResult().getSongs()) {
                Song e = CovertUtil.neteaseCloudSong2Song(song);
                content.add(e);
            }
        }
        result.setList(content);
        result.setTotal(resp.getResult().getSongCount());
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
    public Boolean loginByPhone(String phone, String password) {
        String uri = getUri() + "/login/cellphone?phone=" + phone + "&password=" + password;
        LoginResp resp = restTemplate.getForObject(uri, LoginResp.class);
        if (resp == null) {
            throw new MusicException(500, "调用登录发生未知错误");
        }
        if (resp.getCode() != 200) {
            throw new MusicException(resp.getCode(), "调用登录发生错误[]", resp.getMessage());
        }
        return true;
    }
}
