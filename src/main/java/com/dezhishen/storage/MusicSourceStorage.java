package com.dezhishen.storage;

import com.dezhishen.constant.CacheKey;
import com.dezhishen.domain.MusicSource;
import org.springframework.stereotype.Service;

@Service
public class MusicSourceStorage extends BaseHashStorage<MusicSource> {
    @Override
    protected String getCacheKey() {
        return CacheKey.MUSIC_SOURCE;
    }
}
