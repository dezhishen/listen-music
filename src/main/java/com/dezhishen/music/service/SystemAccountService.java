package com.dezhishen.music.service;

import com.dezhishen.music.domain.SystemAccount;

public interface SystemAccountService extends IBaseService<SystemAccount> {
    /**
     * 注册账号
     *
     * @param account
     * @return
     */
    boolean registerAccount(SystemAccount account);
}
