package com.dezhishen.service.musicsource.impl.qqmusic;

import com.dezhishen.domain.MusicUser;
import com.dezhishen.domain.PlayList;
import com.dezhishen.domain.Song;
import com.dezhishen.service.musicsource.AbstractMusicSourceTemplate;
import com.dezhishen.service.musicsource.constant.MusicSources;
import com.dezhishen.service.musicsource.impl.neteasecloud.NeteaseCloudSong;
import com.dezhishen.service.musicsource.util.CovertUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Song song = CovertUtil.qqMusicSong2Song(resp.getData().getTrackInfo());
        song.setUrl(getSongUrlById(id));
        return song;
    }

    @Getter
    @Setter
    public static class UrlResp {
        private Map<String, String> data;

        public UrlResp() {
        }
    }

    @Override
    public String getSongUrlById(String id) {
        UrlResp resp = restTemplate.getForObject(getUri() + "/song/urls?id=" + id, UrlResp.class);
        if (resp == null || resp.getData() == null) {
            return null;
        }
        return resp.getData().get(id);
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
    public Page<Song> searchSong(String q, Integer pageNum, Integer pageSize) {
        SearchSongResp resp = restTemplate.getForObject(getUri() + "/search?key=" + q + "&pageNo=" + pageNum + "&pageSize=" + pageSize, SearchSongResp.class);
        if (resp == null || resp.getData() == null) {
            return new PageImpl<>(new ArrayList<>());
        }
        List<Song> content = new ArrayList<>();
        if (resp.getData().getList() != null) {
            for (QqMusicSearchSong song : resp.getData().getList()) {
                Song e = CovertUtil.qqMusicSearchSong2Song(song);
                content.add(e);
            }
        }
        return new PageImpl<>(content, PageRequest.of(pageNum, pageSize), resp.getData().getTotal());
    }

    @Override
    public Page<MusicUser> searchMusicUser(String q, String source, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public Page<PlayList> searchPlayList(String q, String source, Integer pageNum, Integer pageSize) {
        return null;
    }
}
