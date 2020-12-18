package com.dezhishen.service.impl;

import com.dezhishen.domain.PlayList;
import com.dezhishen.domain.Song;
import com.dezhishen.exception.MusicException;
import com.dezhishen.service.PlayListService;
import com.dezhishen.service.UserPlayListService;
import com.dezhishen.service.musicsource.MusicSourceProxy;
import com.dezhishen.storage.PlayListStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author dezhishen
 */
@Service
public class PlayListServiceImpl implements PlayListService {
    @Autowired
    private MusicSourceProxy musicSourceProxy;
    @Autowired
    private PlayListStorage playListStorage;
    @Autowired
    private UserPlayListService userPlayListService;

    @Override
    public PlayList save(PlayList playList) {
        if (StringUtils.isEmpty(playList.getName())) {
            throw new MusicException("歌单名称不能为空");
        }
        playListStorage.save(playList);
        userPlayListService.save(playList.getUserId(), playList.getId());
        return playList;
    }

    @Override
    public PlayList get(String id) {
        return playListStorage.get(id);
    }

    @Override
    public List<Song> listSongs(String id) {
        return playListStorage.getSongs(id);
    }

    @Override
    public Song addSong(String id, Song song) {
        Song s = musicSourceProxy.getSongById(song.getSource(), song.getId());
        playListStorage.addSong(id, s);
        return s;
    }

    @Override
    public boolean removeSong(String id, Song song) {
        return playListStorage.removeSong(id, song);
    }

    @Override
    public boolean removeSong(String id, String source, String songId) {
        Song s = new Song();
        s.setId(songId);
        s.setSource(source);
        return removeSong(id, s);
    }

    @Override
    public boolean delete(String id) {
        return playListStorage.delete(id);
    }

    @Override
    public List<PlayList> list(String userId) {
        Collection<String> playListIds = userPlayListService.getUserPlayList(userId);
        if (playListIds == null || playListIds.isEmpty()) {
            return null;
        }
        List<PlayList> result = new ArrayList<>();
        for (String playListId : playListIds) {
            PlayList e = get(playListId);
            if (e != null) {
                result.add(e);
            }
        }
        return result;
    }
}
