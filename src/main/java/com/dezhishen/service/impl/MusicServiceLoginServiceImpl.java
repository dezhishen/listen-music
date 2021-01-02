package com.dezhishen.service.impl;

import com.dezhishen.service.MusicServiceLoginService;
import com.dezhishen.service.musicsource.MusicSourceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicServiceLoginServiceImpl implements MusicServiceLoginService {
    @Autowired
    private MusicSourceProxy proxy;

    @Override
    public Boolean loginByPhone(String source, String phone, String password) {
        return proxy.loginByPhone(source, phone, password);
    }
}
