package com.dezhishen.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dezhishen.music.domain.PlayList;
import com.dezhishen.music.domain.PlayListSong;
import com.dezhishen.music.domain.Song;
import com.dezhishen.music.exception.MusicException;
import com.dezhishen.music.mapper.PlayListMapper;
import com.dezhishen.music.mapper.PlayListSongMapper;
import com.dezhishen.music.service.MusicService;
import com.dezhishen.music.service.PlayListService;
import com.dezhishen.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dezhishen
 */
@Service
public class PlayListServiceImpl extends AbstractServiceImpl<PlayList> implements PlayListService {

    @Autowired
    private PlayListMapper mapper;
    @Autowired
    private PlayListSongMapper playListSongMapper;
    @Autowired
    private MusicService musicService;
    @Autowired
    private SongService songService;

    @Override
    protected BaseMapper<PlayList> mapper() {
        return mapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Song addSongAsync(String playListId, Song song) {
        PlayListSong record = new PlayListSong();
        record.setPlayListId(playListId);
        record.setSource(song.getSource());
        record.setSongId(song.getId());
        if (playListSongMapper.selectCount(new QueryWrapper<>(record)) > 0) {
            return null;
        }
        playListSongMapper.insert(record);
        Song result = musicService.getSongBySourceAndId(record.getSource(), record.getSongId());
        songService.insertAsync(result);
        return result;
    }

    @Override
    public Song addSong(String playListId, Song song) {
        PlayListSong record = new PlayListSong();
        record.setPlayListId(playListId);
        record.setSource(song.getSource());
        record.setSongId(song.getId());
        if (playListSongMapper.selectCount(new QueryWrapper<>(record)) > 0) {
            return null;
        }
        playListSongMapper.insert(record);
        Song result = musicService.getSongBySourceAndId(record.getSource(), record.getSongId());
        songService.insert(result);
        return result;
    }

    @Override
    public boolean removeSong(String playListId, String source, String songId) {
        PlayListSong record = new PlayListSong();
        record.setSongId(songId);
        record.setSource(source);
        record.setPlayListId(playListId);
        playListSongMapper.delete(new QueryWrapper<>(record));
        return true;
    }

    @Override
    public List<Song> selectSongs(String playListId) {
        return playListSongMapper.selectSongByPlayListId(playListId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Song> importSongs(String playListId, String source, String sourcePlayListId) {
        PlayList playList = musicService.getSongsBySourceAndPlayListId(source, sourcePlayListId);
        if (playList == null || playList.getSongs() == null || playList.getSongs().isEmpty()) {
            throw new MusicException("当前传入歌单无歌曲");
        }
        List<Song> result = new ArrayList<>();
        for (Song song : playList.getSongs()) {
            Song e = addSong(playListId, song);
            if (e != null) {
                result.add(e);
            }
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(String id) {
        PlayListSong record = new PlayListSong();
        record.setPlayListId(id);
        playListSongMapper.delete(new UpdateWrapper<>(record));
        return super.delete(id);
    }

    @Override
    public PlayList save(PlayList playList) {
        if (StringUtils.isEmpty(playList.getId())) {
            PlayList r = new PlayList();
            r.setUserId(playList.getUserId());
            if (mapper.selectCount(new QueryWrapper<>(r)) >= 5) {
                throw new MusicException("最多创建5个歌单");
            }
        }
        return super.save(playList);
    }

    @Override
    public List<PlayList> select(PlayList playList) {
        List<PlayList> result = super.select(playList);
        if (result == null || result.isEmpty()) {
            if (!StringUtils.isEmpty(playList.getUserId())) {
                PlayList t = new PlayList();
                t.setUserId(playList.getUserId());
                t.setName("我喜欢的音乐");
                PlayList e = save(playList);
                result = new ArrayList<>();
                result.add(e);
            }
        }
        return result;
    }
}
