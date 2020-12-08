package com.dezhishen.storage;

import com.dezhishen.constant.CacheKey;
import com.dezhishen.domain.SystemUser;
import com.dezhishen.exception.MusicException;
import com.dezhishen.uitl.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author dezhishen
 */
@Service
public class HouseUserStorage {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HouseStorage houseStorage;

    private static String getCacheKey(String houseId) {
        return String.format(CacheKey.HOUSE_USER, houseId);
    }

    /**
     * 拉取所有用户
     *
     * @param houseId
     * @return
     */
    public List<SystemUser> listAll(String houseId) {
        return redisTemplate.opsForHash().values(getCacheKey(houseId));
    }

    /**
     * 加入房间
     *
     * @param user
     * @return
     */
    public SystemUser join(SystemUser user) {
        if (StringUtils.isEmpty(user.getHouseId())) {
            throw new MusicException("未传入房间号");
        }
        if (houseStorage.get(user.getId()) == null) {
            throw new MusicException("未找到房间");
        }
        if (StringUtils.isEmpty(user.getId())) {
            user.setId(UUIDUtil.genUUID());
        }
        redisTemplate.opsForHash().put(getCacheKey(user.getHouseId()), user.getId(), user);
        return user;
    }

    /**
     * leave
     *
     * @param houseId
     * @param userId
     * @return result
     */
    public Boolean leave(String houseId, String userId) {
        redisTemplate.opsForHash().delete(getCacheKey(houseId), userId);
        return true;
    }
}

