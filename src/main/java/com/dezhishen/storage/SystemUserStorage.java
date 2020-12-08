package com.dezhishen.storage;

import com.dezhishen.constant.CacheKey;
import com.dezhishen.domain.SystemUser;
import org.springframework.stereotype.Service;

/**
 * 系统用户持久化入口
 *
 * @author dezhishen
 */
@Service
public class SystemUserStorage extends BaseHashStorage<SystemUser> {

    @Override
    protected String getCacheKey() {
        return CacheKey.SYSTEM_USER;
    }
}
