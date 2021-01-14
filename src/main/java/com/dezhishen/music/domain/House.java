package com.dezhishen.music.domain;

import com.dezhishen.music.base.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * 房间对象
 *
 * @author dezhishen
 */
@Getter
@Setter
public class House extends BaseDomain {
    private String name;
    private String password;
}
