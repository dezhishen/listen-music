package com.dezhishen.music.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dezhishen.music.base.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * 本系统的用户
 *
 * @author shendezhi
 */
@Getter
@Setter
public class SystemUser extends BaseDomain {
    private String name;
    @TableField(exist = false)
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
