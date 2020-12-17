package com.dezhishen.constant;

/**
 * 缓存的key
 *
 * @author dezhishen
 */
public class CacheKey {
    /**
     * 房间
     */
    public static final String HOUSE = "_house";
    /**
     * 歌曲
     */
    public static final String SONG = "_song";
    /**
     * 系统用户
     */
    public static final String SYSTEM_USER = "_user";
    /**
     * 房间内的用户
     */
    public static final String HOUSE_USER = "_house_user:%s";

    /**
     * 播放列表
     */
    public static final String PLAY_LIST = "_play_list";
    /**
     * 播放列表中的歌曲
     * _play_list_songs:{playListId}
     */
    public static final String PLAY_LIST_SONGS = "_play_list_songs:%s";
    /**
     * 饼干
     */
    public static final String BISCUIT = "_biscuit";
    /**
     * 源
     */
    public static final String MUSIC_SOURCE = "_music_source";
}
