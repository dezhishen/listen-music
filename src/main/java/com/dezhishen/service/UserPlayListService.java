package com.dezhishen.service;

import java.util.Collection;

public interface UserPlayListService {

    void save(String userId, String playListId);

    boolean remove(String userId, String playListId);

    Collection<String> getUserPlayList(String userId);
}
