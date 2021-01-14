package com.dezhishen.music.service;

import com.dezhishen.music.domain.MusicUser;
import com.dezhishen.music.domain.PlayList;
import com.dezhishen.music.domain.Song;
import com.github.pagehelper.PageInfo;

/**
 * 音乐服务
 *
 * @author dezhishen
 */
public interface MusicService {
    /**
     * 搜索音乐
     *
     * @param condition
     * @param pageNum
     * @param source    来源
     * @param pageSize
     * @return
     */
    PageInfo<Song> searchSong(String condition, String source, Integer pageNum, Integer pageSize);

    /**
     * 搜索音乐服务中的用户
     *
     * @param q
     * @param source
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<MusicUser> searchMusicUser(String q, String source, Integer pageNum, Integer pageSize);


    /**
     * @param q
     * @param source
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<PlayList> searchPlayList(String q, String source, Integer pageNum, Integer pageSize);

    /**
     * 获取音乐
     *
     * @param source 来源
     * @param id     id
     * @return song
     */
    Song getSongBySourceAndId(String source, String id);

    /**
     * 获取 音乐的播放地址
     *
     * @param source 来源
     * @param id     id
     * @return url地址
     */
    String getSongUrlBySourceAndId(String source, String id);
}
