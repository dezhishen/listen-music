package com.dezhishen.music.storage;

import com.dezhishen.music.constant.CacheKey;
import com.dezhishen.music.domain.MusicSource;
import org.springframework.stereotype.Service;

@Service
public class MusicSourceStorage extends BaseCacheStorage<MusicSource> {
    @Override
    protected String getCacheKey() {
        return CacheKey.MUSIC_SOURCE;
    }
}
