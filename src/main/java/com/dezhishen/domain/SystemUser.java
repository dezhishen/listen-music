package com.dezhishen.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 本系统的用户
 *
 * @author shendezhi
 */
@Getter
@Setter
public class SystemUser {
    private String id;
    private String name;
    private String houseId;
    private Map<String, String> properties;

    public String getProperty(String key) {
        if (properties == null || properties.isEmpty()) {
            return null;
        }
        return properties.get(key);
    }
}
