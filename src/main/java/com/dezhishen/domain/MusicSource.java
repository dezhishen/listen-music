package com.dezhishen.domain;

import com.dezhishen.base.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dezhishen
 */
@Setter
@Getter
public class MusicSource extends BaseDomain {
    private boolean enabled;
    private String uri;
    private String description;
}
