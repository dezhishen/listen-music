package com.dezhishen.service.musicsource.impl.qqmusic;

import com.dezhishen.domain.MusicUser;
import com.dezhishen.domain.PlayList;
import com.dezhishen.domain.Song;
import com.dezhishen.service.musicsource.AbstractMusicSourceTemplate;
import com.dezhishen.service.musicsource.constant.MusicSources;
import com.dezhishen.service.musicsource.util.CovertUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

@Service
public class QqMusicSourceClient extends AbstractMusicSourceTemplate {
    @Override
    public String getSource() {
        return MusicSources.QQ_MUSIC;
    }

    @Setter
    @Getter
    public static final class Resp {
        @JsonProperty("track_info")
        private QqMusicSong trackInfo;

        public Resp() {
        }
    }

    @Setter
    @Getter
    public static class SongResp {
        private Resp data;

        public SongResp() {
        }
    }

    @Override
    public Song getSongById(String id) {
        SongResp resp = restTemplate.getForObject(getUri() + "/song?songmid=" + id, SongResp.class);
        if (resp == null || resp.getData() == null || resp.getData().getTrackInfo() == null) {
            return null;
        }
        return CovertUtil.qqMusicSong2Song(resp.getData().getTrackInfo());
    }

    @Getter
    @Setter
    public static class UrlResp {
        private String data;

        public UrlResp() {
        }
    }

    @Override
    public String getSongUrlById(String id) {
        UrlResp resp = restTemplate.getForObject(getUri() + "/song/url?id=" + id, UrlResp.class);
        if (resp == null || resp.getData() == null) {
            return null;
        }
        return resp.getData();
    }


    @Setter
    @Getter
    public static class PageResp<T> {
        private List<T> list;
        private long total;

        public PageResp() {
        }
    }


    @Setter
    @Getter
    public static class SearchSongPageResp extends PageResp<QqMusicSearchSong> {
        public SearchSongPageResp() {
        }
    }

    @Setter
    @Getter
    public static class SearchSongResp {
        private int result;
        private SearchSongPageResp data;

        public SearchSongResp() {
        }
    }

    @Override
    public PageInfo<Song> searchSong(String q, Integer pageNum, Integer pageSize) {
        SearchSongResp resp = restTemplate.getForObject(getUri() + "/search?key=" + q + "&pageNo=" + pageNum + "&pageSize=" + pageSize, SearchSongResp.class);
        PageInfo<Song> result = new PageInfo<>();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        if (resp == null || resp.getData() == null) {
            result.setTotal(0);
            return result;
        }
        List<Song> content = new ArrayList<>();
        if (resp.getData().getList() != null) {
            for (QqMusicSearchSong song : resp.getData().getList()) {
                Song e = CovertUtil.qqMusicSearchSong2Song(song);
                content.add(e);
            }
        }
        result.setList(content);
        result.setTotal(resp.getData().getTotal());
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
        throw new NotImplementedException();
    }
}
