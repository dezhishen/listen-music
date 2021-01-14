package com.dezhishen.music.service;

import com.dezhishen.music.domain.MusicSource;

import java.util.List;

public interface MusicSourceService {
    /**
     * 获取
     *
     * @param id
     * @return
     */
    MusicSource get(String id);

    /**
     * 创建
     *
     * @param musicSource
     * @return
     */
    MusicSource save(MusicSource musicSource);

    List<MusicSource> list();
}
