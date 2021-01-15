package com.dezhishen.music.service;

import com.dezhishen.music.domain.SystemAccount;

public interface SystemAccountService extends IBaseService<SystemAccount>{
    boolean registerAccount(SystemAccount account);
    void insertAsync(SystemAccount account);
}
