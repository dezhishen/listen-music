package com.dezhishen.service.musicsource.impl.jsonpath;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

import java.util.Map;

@Setter
@Getter
public class MusicServerProperty {
    @Setter
    @Getter
    public static class Uri {
        private String path;
        private HttpMethod method;
    }

    private Map<String, Uri> uris;
    private String baseUri;
    private boolean enabled;

    public Uri getUri(String name) {
        return uris.get(name);
    }
}
