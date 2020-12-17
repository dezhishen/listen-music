package com.dezhishen.interceptor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "biscuit-filter")
public class BiscuitFilterConfig {
    private Set<String> ignore = new HashSet<>();
}
