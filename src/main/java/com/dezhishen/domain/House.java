package com.dezhishen.domain;

import com.dezhishen.base.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
