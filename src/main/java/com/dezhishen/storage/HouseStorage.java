package com.dezhishen.storage;

import com.dezhishen.constant.CacheKey;
import com.dezhishen.domain.House;
import org.springframework.stereotype.Service;

/**
 * @author dezhishen
 */
@Service
public class HouseStorage extends BaseHashStorage<House> {
    @Override
    protected String getCacheKey() {
        return CacheKey.HOUSE;
    }
}
