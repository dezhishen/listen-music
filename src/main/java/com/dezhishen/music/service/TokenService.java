package com.dezhishen.music.service;

import com.dezhishen.music.domain.SystemAccount;
import com.dezhishen.music.dto.LoginResult;

public interface TokenService {

    /**
     * 根据token查询账号
     *
     * @param token
     * @return
     */
    SystemAccount queryAccountByToken(String token);

    /**
     * 移除token
     * @param token
     */
    void removeToken(String token);

    /**
     * 生成token
     *
     * @param account
     * @return
     */
    LoginResult createToken(SystemAccount account);


    LoginResult refreshToken(String refreshToken);
}
