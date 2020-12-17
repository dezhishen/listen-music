package com.dezhishen.service.impl;

import com.dezhishen.domain.MusicSource;
import com.dezhishen.service.MusicSourceService;
import com.dezhishen.storage.MusicSourceStorage;
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
