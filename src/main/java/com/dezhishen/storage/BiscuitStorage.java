package com.dezhishen.storage;

import com.dezhishen.constant.CacheKey;
import com.dezhishen.domain.Biscuit;
import com.dezhishen.uitl.UUIDUtil;
import org.springframework.stereotype.Service;

@Service
public class BiscuitStorage extends BaseHashStorage<Biscuit> {
    @Override
    protected String getCacheKey() {
        return CacheKey.BISCUIT;
    }

    @Override
    protected String genUUID() {
        return UUIDUtil.genShortUUID();
    }
}
