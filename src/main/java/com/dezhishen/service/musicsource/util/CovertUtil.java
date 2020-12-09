package com.dezhishen.service.musicsource.util;

import com.dezhishen.domain.Song;
import com.dezhishen.service.musicsource.impl.neteasecloud.NeteaseCloudSong;

/**
 * 转换工具类
 *
 * @author dezhishen
 */
public class CovertUtil {
    /**
     * 网易云返回对象转换为系统内部对象
     *
     * @param song 外部对象
     * @return 内部对象
     */
    public static Song neteaseCloudSong2Song(NeteaseCloudSong song) {
        if (song == null) {
            return null;
        }
        Song result = new Song();
        result.setName(song.getName());
        result.setId(song.getId());
        return result;
    }
}

