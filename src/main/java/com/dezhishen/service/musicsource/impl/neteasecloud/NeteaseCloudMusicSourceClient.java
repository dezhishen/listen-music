package com.dezhishen.service.musicsource.impl.neteasecloud;

import com.dezhishen.domain.Song;
import com.dezhishen.service.musicsource.AbstractMusicSourceTemplate;
import com.dezhishen.service.musicsource.constant.MusicSources;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

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
    private static class NeteaseCloudMusics {
        private List<NeteaseCloudSong> songs;

    }

    @Override
    public Song getSongById(String id) {
        NeteaseCloudMusics resp = restTemplate.getForObject(getUri() + "/song/detail/?ids=" + id, NeteaseCloudMusics.class);
        if (resp == null || resp.getSongs() == null || resp.getSongs().isEmpty()) {
            return null;
        }
        NeteaseCloudSong music = resp.getSongs().get(0);
        Song result = new Song();
        result.setSource(getSource());
        result.setName(music.getName());
        result.setId(music.getId());
        result.setUrl(getSongUrlById(id));
        return result;
    }

    @Getter
    @Setter
    private static class NeteaseCloudUrlResp {
        private List<NeteaseCloudUrl> data;
    }

    @Override
    public String getSongUrlById(String id) {
        NeteaseCloudUrlResp resp = restTemplate.getForObject(getUri() + "/song/url?id=" + id, NeteaseCloudUrlResp.class);
        if (resp == null || resp.getData() == null || resp.getData().isEmpty()) {
            return null;
        }
        return resp.getData().get(0).getUrl();
    }
}
