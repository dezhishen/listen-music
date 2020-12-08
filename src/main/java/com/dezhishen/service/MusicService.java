package com.dezhishen.service;

import com.dezhishen.domain.Music;
import com.dezhishen.domain.MusicUser;
import com.dezhishen.domain.PlayList;
import org.springframework.data.domain.Page;

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
    Page<Music> searchMusic(String condition, String source, Integer pageNum, Integer pageSize);

    /**
     * 搜索音乐服务中的用户
     *
     * @param q
     * @param source
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<MusicUser> searchMusicUser(String q, String source, Integer pageNum, Integer pageSize);


    /**
     * @param q
     * @param source
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<PlayList> searchPlayList(String q, String source, Integer pageNum, Integer pageSize);
}
