package com.dezhishen.music.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dezhishen.music.domain.SystemUser;
import com.dezhishen.music.mapper.SystemUserMapper;
import com.dezhishen.music.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl extends AbstractServiceImpl<SystemUser> implements SystemUserService {
    @Autowired
    private SystemUserMapper mapper;

    @Override
    protected BaseMapper<SystemUser> mapper() {
        return mapper;
    }

    @Override
    public SystemUser insert(SystemUser systemUser) {
        SystemUser record = new SystemUser();
        record.setId(systemUser.getId());
        record.setName(systemUser.getName());
        mapper.insert(record);
        return record;
    }

    @Override
    @Async
    public void insertAsync(SystemUser systemUser) {
        insert(systemUser);
    }


}
