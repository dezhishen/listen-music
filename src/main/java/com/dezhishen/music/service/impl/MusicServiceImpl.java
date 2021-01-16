package com.dezhishen.music.service.impl;

import com.dezhishen.music.domain.MusicUser;
import com.dezhishen.music.domain.PlayList;
import com.dezhishen.music.domain.Song;
import com.dezhishen.music.service.MusicService;
import com.dezhishen.music.service.musicsource.IMusicResourceServiceProxy;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dezhishen
 */
@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private IMusicResourceServiceProxy sourceProxy;

    @Override
    public PageInfo<Song> searchSong(String q, String source, Integer pageNum, Integer pageSize) {
        return sourceProxy.searchSong(q, source, pageNum, pageSize);
    }

    @Override
    public PageInfo<MusicUser> searchMusicUser(String q, String source, Integer pageNum, Integer pageSize) {
        return sourceProxy.searchMusicUser(q, source, pageNum, pageSize);
    }

    @Override
    public PageInfo<PlayList> searchPlayList(String q, String source, Integer pageNum, Integer pageSize) {
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

    @Override
    public PlayList getSongsBySourceAndPlayListId(String source, String sourcePlayListId) {
        return sourceProxy.getSongsBySourceAndPlayListId(source, sourcePlayListId);
    }
}
