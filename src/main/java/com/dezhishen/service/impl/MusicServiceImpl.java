package com.dezhishen.service.impl;

import com.dezhishen.domain.MusicUser;
import com.dezhishen.domain.PlayList;
import com.dezhishen.domain.Song;
import com.dezhishen.service.MusicService;
import com.dezhishen.service.musicsource.MusicSourceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author dezhishen
 */
@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicSourceProxy sourceProxy;

    @Override
    public Page<Song> searchSong(String q, String source, Integer pageNum, Integer pageSize) {
        return sourceProxy.searchSong(q, source, pageNum, pageSize);
    }

    @Override
    public Page<MusicUser> searchMusicUser(String q, String source, Integer pageNum, Integer pageSize) {
        return sourceProxy.searchMusicUser(q, source, pageNum, pageSize);
    }

    @Override
    public Page<PlayList> searchPlayList(String q, String source, Integer pageNum, Integer pageSize) {
        return sourceProxy.searchPlayList(q, source, pageNum, pageSize);
    }

    @Override
    public Song getSongBySourceAndId(String source, String id) {
        return sourceProxy.getSongById(source, id);
    }

    @Override
    public String getSongUrlBySourceAndId(String source, String id) {
        return sourceProxy.getSongUrlById(source, id);
    }
}
