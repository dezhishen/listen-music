package com.dezhishen.storage;


import com.dezhishen.base.BaseDomain;
import com.dezhishen.uitl.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @param <T>
 * @author dezhishen
 */
public abstract class BaseHashStorage<T extends BaseDomain> {
    protected abstract String getCacheKey();

    protected String genUUID() {
        return UUIDUtil.genUUID();
    }

    @Autowired
    private RedisTemplate redisTemplate;

    public T save(T t) {
        if (StringUtils.isEmpty(t.getId())) {
            t.setId(genUUID());
        }
        redisTemplate.opsForHash().put(getCacheKey(), t.getId(), t);
        return t;
    }

    public Boolean delete(String id) {
        redisTemplate.opsForHash().delete(getCacheKey(), id);
        return true;
    }

    public List<T> listAll() {
        HashOperations<String, String, T> operations = redisTemplate.opsForHash();
        return operations.values(getCacheKey());
    }

    public T get(String id) {
        HashOperations<String, String, T> operations = redisTemplate.opsForHash();
        return operations.get(getCacheKey(), id);
    }
}
