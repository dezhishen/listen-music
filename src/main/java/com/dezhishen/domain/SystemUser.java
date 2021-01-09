package com.dezhishen.domain;

import com.dezhishen.base.BaseDomain;
import com.dezhishen.constant.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.util.Map;

/**
 * 本系统的用户
 *
 * @author shendezhi
 */
@Getter
@Setter
public class SystemUser extends BaseDomain {
    private String name;
    @Transient
    private String houseId;
//    private String role = Role.NORMAL;
//    private Map<String, String> properties;

//    public String getProperty(String key) {
//        if (properties == null || properties.isEmpty()) {
//            return null;
//        }
//        return properties.get(key);
//    }
}
