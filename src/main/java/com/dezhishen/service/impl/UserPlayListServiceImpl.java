package com.dezhishen.service.impl;

import com.dezhishen.domain.UserPlayList;
import com.dezhishen.service.UserPlayListService;
import com.dezhishen.storage.UserPlayListStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserPlayListServiceImpl implements UserPlayListService {
    @Autowired
    private UserPlayListStorage userPlayListStorage;

    @Override
    public void save(String userId, String playListId) {
        userPlayListStorage.save(userId, playListId);
    }

    @Override
    public boolean remove(String userId, String playListId) {
        return userPlayListStorage.delete(userId, playListId);
    }

    @Override
    public Collection<String> getUserPlayList(String userId) {
        return userPlayListStorage.getUserPlayList(userId);
    }
}
