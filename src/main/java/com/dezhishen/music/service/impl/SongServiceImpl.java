package com.dezhishen.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dezhishen.music.domain.Artist;
import com.dezhishen.music.domain.Song;
import com.dezhishen.music.mapper.SongMapper;
import com.dezhishen.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl extends AbstractServiceImpl<Song> implements SongService {
    @Autowired
    private SongMapper mapper;

    @Override
    protected BaseMapper<Song> mapper() {
        return mapper;
    }

    @Override
    public Song insert(Song song) {
        Song record = new Song();
        record.setId(song.getId());
        record.setSource(song.getSource());
        processArtistsName(song);
        if (mapper.selectCount(new QueryWrapper<>(record)) > 0) {
            mapper.update(song, new UpdateWrapper<>(record));
        }else {
            mapper.insert(song);
        }
        return song;
    }

    @Override
    public Song update(Song song) {
        Song record = new Song();
        record.setId(song.getId());
        record.setSource(song.getSource());
        processArtistsName(song);
        mapper.update(song, new UpdateWrapper<>(record));
        return song;
    }

    private void processArtistsName(Song song) {
        if (song.getArtists() != null && !song.getArtists().isEmpty()) {
            StringBuilder name = new StringBuilder();
            for (Artist artist : song.getArtists()) {
                name.append(artist.getName()).append(",");
            }
            song.setArtistsName(name.substring(0, name.length() - 1));
        }
    }

    @Override
    @Async
    public void insertAsync(Song result) {
        insert(result);
    }
}
