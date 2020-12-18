package com.dezhishen.storage;

import com.dezhishen.domain.UserPlayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserPlayListStorage {
    @Autowired
    private RedisTemplate redisTemplate;

    private static String getCacheKey(String userId) {
        return "";
    }

    public void save(String userId, String playListId) {
        redisTemplate.opsForSet().add(getCacheKey(userId), playListId);
    }

    public boolean delete(String userId, String playListId) {
        redisTemplate.opsForSet().remove(getCacheKey(userId), playListId);
        return true;
    }

    public Collection<String> getUserPlayList(String userId) {
        return redisTemplate.opsForSet().members(userId);
    }
}
