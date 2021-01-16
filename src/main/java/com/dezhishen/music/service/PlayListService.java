package com.dezhishen.music.service;

import com.dezhishen.music.controller.PlayListController;
import com.dezhishen.music.domain.PlayList;
import com.dezhishen.music.domain.Song;

import java.util.List;

/**
 * 歌单服务
 *
 * @author dezhishen
 */
public interface PlayListService extends IBaseService<PlayList> {
    Song addSongAsync(String playListId, Song song);

    Song addSong(String playListId, Song song);

    boolean removeSong(String playListId, String source, String songId);

    List<Song> selectSongs(String playListId);

    List<Song> importSongs(String playListId, String source, String sourcePlayListId);
}
