package com.dezhishen.music.constant;

/**
 * 缓存的key
 *
 * @author dezhishen
 */
public class CacheKey {
    /**
     * 间隔符
     */
    public static final String SEPARATOR = "::";
    /**
     * 歌曲
     */
    public static final String SONG = "_song";
    /**
     * 饼干
     */
    public static final String BISCUIT = "_biscuit";
    /**
     * 源
     */
    public static final String MUSIC_SOURCE = "_music_source";

    /**
     * 音乐播放地址
     */
    public static final String MUSIC_URL = "_music_url";


    public static final String ACCESS_TOKEN = "access_token_";

    public static final String REFRESH_TOKEN = "refresh_token_";

    public static final String ACCOUNT_ACCESS_TOKEN = "account_access_token_";

    public static String getKeyBySourceAndId(String source, String id) {
        return String.format("%s" + CacheKey.SEPARATOR + "%s", source, id);
    }
}
