package com.dezhishen.service.musicsource.conf;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class MusicApiGetSongConfig extends BaseMusicApiConfig {
    private String id;
    private String name;
    private JsonPathArtistsConfig artists;
    private boolean processProperties;
    private Map<String, String> propertiesAlias;
}
