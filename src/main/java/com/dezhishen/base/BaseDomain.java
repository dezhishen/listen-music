package com.dezhishen.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BaseDomain implements Serializable {
    private String id;
}
