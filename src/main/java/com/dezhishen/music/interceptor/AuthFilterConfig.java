package com.dezhishen.music.interceptor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "auth")
public class AuthFilterConfig {
    private Set<String> ignore = new HashSet<>();
    private boolean enabled;
}
