package com.dezhishen.music.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dezhishen.music.base.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * 账号
 */
@Setter
@Getter
public class SystemAccount extends BaseDomain {
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 密码
     */
    private String password;
    /**
     * 加密盐
     */
    private String salt;
    /**
     * 用户id
     */
    private String userId;

    @TableField(exist = false)
    private String nickName;
}
