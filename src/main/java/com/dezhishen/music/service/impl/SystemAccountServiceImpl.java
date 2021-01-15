package com.dezhishen.music.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dezhishen.music.domain.SystemAccount;
import com.dezhishen.music.domain.SystemUser;
import com.dezhishen.music.exception.MusicException;
import com.dezhishen.music.mapper.SystemAccountMapper;
import com.dezhishen.music.service.SystemAccountService;
import com.dezhishen.music.service.SystemUserService;
import com.dezhishen.music.uitl.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

// change 补充注解
@Service
public class SystemAccountServiceImpl extends AbstractServiceImpl<SystemAccount> implements SystemAccountService {

    @Autowired
    private SystemAccountMapper systemAccountMapper;

    @Autowired
    private SystemUserService systemUserService;

    @Override
    protected BaseMapper<SystemAccount> mapper() {
        return systemAccountMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean registerAccount(SystemAccount account) {
        String nickName = account.getNickName();
        String loginName = account.getLoginName();
        if (StringUtils.isEmpty(nickName)) {
            throw new MusicException("未传入昵称");
        }
        if (StringUtils.isEmpty(loginName)) {
            throw new MusicException("未传入账户名");
        }
        if (StringUtils.isEmpty(account.getPassword())) {
            throw new MusicException("未传入密码");
        }
        SystemAccount condition = new SystemAccount();
        condition.setLoginName(loginName);
        //change 只需要判断账号是否重复即可 使用selectCount即可
        if (systemAccountMapper.selectCount(new QueryWrapper<>(condition)) > 0) {
            throw new MusicException("账号名[%s]已存在", loginName);
        }
        SystemUser user = new SystemUser();
        user.setName(nickName);
        //change 不调用异步方法
        systemUserService.insert(user);
        SystemAccount normalizedAccount = new SystemAccount();
        normalizedAccount.setLoginName(loginName);
        normalizedAccount.setNickName(nickName);

        String salt = RandomUtil.randomString(6);
        normalizedAccount.setSalt(salt);
        normalizedAccount.setPassword(PasswordUtil.encryptPassword(loginName, salt, account.getPassword()));
        //change 设置用户id
        normalizedAccount.setUserId(user.getId());
        //直接调用mapper.insert
        mapper().insert(account);
//        insertAsync(normalizedAccount);
        return true;
    }
//
//    @Override
//    @Async
//    public void insertAsync(SystemAccount account) {
//        insert(account);
//    }


}
