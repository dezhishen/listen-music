package com.dezhishen.service.musicsource.conf;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicApiGetSongConfig extends BaseMusicApiConfig {
    private String id;
    private String name;
    private JsonPathArtistsConfig artists;
}
