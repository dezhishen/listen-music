package com.dezhishen.music.service.impl;

import com.dezhishen.music.domain.MusicSource;
import com.dezhishen.music.service.MusicSourceService;
import com.dezhishen.music.storage.MusicSourceStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicSourceServiceImpl implements MusicSourceService {
    @Autowired
    private MusicSourceStorage musicSourceStorage;

    @Override
    public MusicSource get(String id) {
        return musicSourceStorage.get(id);
    }

    @Override
    public MusicSource save(MusicSource musicSource) {
        return musicSourceStorage.save(musicSource);
    }

    @Override
    public List<MusicSource> list() {
        return musicSourceStorage.listAll();
    }
}
