package com.dezhishen.music.service.musicsource.conf;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MusicServerProperty {
    private String baseUri;
    private String label;
    private boolean enabled;
    private MusicApiConfig api;
}
