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

@Service
public class QqMusicSourceClient extends AbstractMusicSourceTemplate {
    @Override
    public String getSource() {
        return MusicSources.QQ_MUSIC;
    }

    @Setter
    @Getter
    private static class SongResp {
        @Setter
        @Getter
        private static final class Resp {
            @JsonProperty("track_info")
            private QqMusicSong trackInfo;
        }

        private Resp data;
    }

    @Override
    public Song getSongById(String id) {
        SongResp resp = restTemplate.getForObject(getUri() + "/song?songmid=" + id, SongResp.class);
        if (resp == null || resp.getData() == null || resp.getData().getTrackInfo() == null) {
            return null;
        }
        return CovertUtil.qqMusicSong2Song(resp.getData().getTrackInfo());
    }

    @Override
    public String getSongUrlById(String id) {
        return null;
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
