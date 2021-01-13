package com.dezhishen.service.musicsource.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

@Setter
@Getter
public class BaseMusicApiConfig {
    private String uri;
    private HttpMethod method;
    private String root;
}
