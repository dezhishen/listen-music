package com.dezhishen.music.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class LoginRequest implements Serializable {
    @ApiModelProperty("用户名")
    private String loginName;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty(hidden = true)
    private String type;
}
