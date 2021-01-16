package com.dezhishen.music.service.musicsource.conf;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MusicApiGetPlayListConfig extends BaseMusicApiConfig {

    @Setter
    @Getter
    public static class SongsConfig {
        private String root;
        private String name;
        private String id;
    }

    private String id;
    private String name;
    private String description;
    private SongsConfig songs;
}
