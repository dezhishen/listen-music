package com.dezhishen.service;

import com.dezhishen.domain.PlayList;
import com.dezhishen.domain.Song;

import java.util.List;

/**
 * 歌单服务
 *
 * @author dezhishen
 */
public interface PlayListService {

    /**
     * 保存歌单
     *
     * @param playList
     * @return
     */
    PlayList save(PlayList playList);

    /**
     * 获取歌单
     *
     * @param id
     * @return
     */
    PlayList get(String id);


    /**
     * 获取歌单中的音乐列表
     *
     * @param id 歌单id
     * @return
     */
    List<Song> listSongs(String id);

    /**
     * 添加歌曲到歌单
     *
     * @param id
     * @param song
     * @return
     */
    Song addSong(String id, Song song);

    /**
     * 从歌单中移除歌曲
     *
     * @param id   歌单id
     * @param song 歌曲
     * @return
     */
    boolean removeSong(String id, Song song);

    /**
     * 从歌单中移除歌曲
     *
     * @param id     歌单id
     * @param source 歌曲源
     * @param songId 歌曲id
     * @return
     */
    boolean removeSong(String id, String source, String songId);

    /**
     * 删除歌单
     *
     * @param id
     * @return
     */
    boolean delete(String id);
}
