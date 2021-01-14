package com.dezhishen.music.service.musicsource.conf;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicApiSearchSongConfig extends BaseMusicApiConfig {
    private String total;
    private SongConfig list;

    @Setter
    @Getter
    public static class SongConfig {
        private String root;
        private String id;
        private String name;
        private ArtistConfig artists;
    }

    @Getter
    @Setter
    public static class ArtistConfig {
        private String root;
        private String id;
        private String name;
        private String picUrl;
    }


}
