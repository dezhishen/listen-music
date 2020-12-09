package com.dezhishen.service.impl;

import com.dezhishen.domain.SystemUser;
import com.dezhishen.service.SystemUserService;
import com.dezhishen.storage.SystemUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl implements SystemUserService {
    @Autowired
    private SystemUserStorage systemUserStorage;

    @Override
    public SystemUser get(String id) {
        return systemUserStorage.get(id);
    }

    @Override
    public SystemUser save(SystemUser systemUser) {
        return systemUserStorage.save(systemUser);
    }

    @Override
    public boolean delete(String id) {
        return systemUserStorage.delete(id);
    }
}
