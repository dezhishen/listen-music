package com.dezhishen.service.musicsource.impl.qqmusic;

import com.dezhishen.domain.MusicUser;
import com.dezhishen.domain.PlayList;
import com.dezhishen.domain.Song;
import com.dezhishen.service.musicsource.AbstractMusicSourceTemplate;
import com.dezhishen.service.musicsource.constant.MusicSources;
import com.dezhishen.service.musicsource.constant.QqMusicSong;
import com.dezhishen.service.musicsource.util.CovertUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class QqMusicSourceClient extends AbstractMusicSourceTemplate {
    @Override
    public String getSource() {
        return MusicSources.QQ_MUSIC;
    }

    @Setter
    @Getter
    private static final class Resp {
        @JsonProperty("track_info")
        private QqMusicSong trackInfo;
    }

    @Setter
    @Getter
    private static class SongResp {
        private Resp data;
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
    private static class UrlResp {
        private Map<String, String> data;
    }

    @Override
    public String getSongUrlById(String id) {
        UrlResp resp = restTemplate.getForObject(getUri() + "/song/urls?id=" + id, UrlResp.class);
        if (resp == null || resp.getData() == null) {
            return null;
        }
        return resp.getData().get(id);
    }

    @Override
    public Page<Song> searchSong(String q, Integer pageNum, Integer pageSize) {
        return null;
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
