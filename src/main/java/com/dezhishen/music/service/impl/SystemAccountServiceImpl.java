package com.dezhishen.music.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dezhishen.music.domain.SystemAccount;
import com.dezhishen.music.domain.SystemUser;
import com.dezhishen.music.exception.MusicException;
import com.dezhishen.music.mapper.SystemAccountMapper;
import com.dezhishen.music.mapper.SystemUserMapper;
import com.dezhishen.music.service.SystemAccountService;
import com.dezhishen.music.service.SystemUserService;
import com.dezhishen.music.uitl.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SystemAccountServiceImpl extends AbstractServiceImpl<SystemAccount> implements SystemAccountService {

    @Autowired
    private SystemAccountMapper systemAccountMapper;

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private SystemUserService systemUserService;

    @Override
    protected BaseMapper<SystemAccount> mapper() {
        return systemAccountMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean registerAccount(SystemAccount account){
        String nickName = account.getNickName();
        String loginName = account.getLoginName();

        List<SystemUser>  existSysUserNames = systemUserMapper.selectSystemUserByName(nickName);
        if(existSysUserNames.size() > 0)
            throw new MusicException("昵称[%s]已存在",nickName);

        SystemUser user = new SystemUser();
        user.setName(nickName);
        systemUserService.insertAsync(user);

        List<SystemAccount> existSysLoginNames = systemAccountMapper.selectSystemAccountByName(loginName);
        if(existSysLoginNames.size() > 0)
            throw new MusicException("用户登录名[%s]已存在",nickName);

        SystemAccount normalizedAccount = new SystemAccount();
        normalizedAccount.setLoginName(loginName);
        normalizedAccount.setNickName(nickName);

        String salt =  RandomUtil.randomString(6);
        normalizedAccount.setSalt(salt);
        normalizedAccount.setPassword(PasswordUtil.encryptPassword(loginName,salt,account.getPassword()));

        insertAsync(normalizedAccount);

        return true;
    }

    @Override
    @Async
    public void insertAsync(SystemAccount account){
        insert(account);
    }


}
