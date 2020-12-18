package com.dezhishen.service.musicsource.impl.neteasecloud;

import com.dezhishen.domain.MusicUser;
import com.dezhishen.domain.PlayList;
import com.dezhishen.domain.Song;
import com.dezhishen.service.musicsource.AbstractMusicSourceTemplate;
import com.dezhishen.service.musicsource.constant.MusicSources;
import com.dezhishen.service.musicsource.util.CovertUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * NeteaseCloud 客户端
 *
 * @author dezhishen
 */
@Service
public class NeteaseCloudMusicSourceClient extends AbstractMusicSourceTemplate {
    @Override
    public String getSource() {
        return MusicSources.NETEASE_CLOUD;
    }

    /**
     * 音乐详情返回结果
     */
    @Setter
    @Getter
    public static class NeteaseCloudMusics {
        private List<NeteaseCloudSong> songs;
        private Long songCount;
        private Boolean hasMore;

        public NeteaseCloudMusics() {
        }
    }

    @Override
    public Song getSongById(String id) {
        NeteaseCloudMusics resp = restTemplate.getForObject(getUri() + "/song/detail/?ids=" + id, NeteaseCloudMusics.class);
        if (resp == null || resp.getSongs() == null || resp.getSongs().isEmpty()) {
            return null;
        }
        NeteaseCloudSong music = resp.getSongs().get(0);
        Song result = CovertUtil.neteaseCloudSong2Song(music);
        result.setSource(getSource());
        result.setUrl(getSongUrlById(id));
        return result;
    }

    @Getter
    @Setter
    public static class NeteaseCloudUrlResp {
        private List<NeteaseCloudUrl> data;

        public NeteaseCloudUrlResp() {
        }
    }

    @Override
    public String getSongUrlById(String id) {
        NeteaseCloudUrlResp resp = restTemplate.getForObject(getUri() + "/song/url?id=" + id, NeteaseCloudUrlResp.class);
        if (resp == null || resp.getData() == null || resp.getData().isEmpty()) {
            return null;
        }
        return resp.getData().get(0).getUrl();
    }

    @Getter
    @Setter
    public static class SearchSongResp {
        private int code;
        private NeteaseCloudMusics result;

        public SearchSongResp() {
        }
    }

    @Override
    public Page<Song> searchSong(String q, Integer pageNum, Integer pageSize) {
        SearchSongResp resp = restTemplate.getForObject(getUri() + "/search?keywords=" + q + "&offset=" + (pageNum - 1) * pageSize + "&limit=" + pageNum * pageSize, SearchSongResp.class);
        if (resp == null || resp.getResult() == null) {
            return Page.empty();
        }
        List<Song> content = new ArrayList<>();
        if (resp.getResult().getSongs() != null) {
            for (NeteaseCloudSong song : resp.getResult().getSongs()) {
                Song e = CovertUtil.neteaseCloudSong2Song(song);
                content.add(e);
            }
        }
        return new PageImpl<>(content, PageRequest.of(pageNum - 1, pageSize), resp.getResult().getSongCount());
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
