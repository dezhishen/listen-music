package com.dezhishen.music.service;

import com.dezhishen.music.domain.SystemAccount;
import com.dezhishen.music.dto.LoginRequest;
import com.dezhishen.music.dto.LoginResult;

public interface SystemAccountService extends IBaseService<SystemAccount> {
    /**
     * 注册账号
     *
     * @param account
     * @return
     */
    boolean registerAccount(SystemAccount account);

    /**
     * 登录
     *
     * @param loginRequest
     * @return
     */
    LoginResult login(LoginRequest loginRequest);

    /**
     * 登出
     * @param token
     * @return
     */
    Boolean logout(String token);
}
