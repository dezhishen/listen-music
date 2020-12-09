package com.dezhishen.storage;

import com.dezhishen.constant.CacheKey;
import com.dezhishen.domain.PlayList;
import com.dezhishen.domain.Song;
import com.dezhishen.exception.MusicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 歌单持久化
 *
 * @author dezhishen
 */
@Service
public class PlayListStorage extends BaseHashStorage<PlayList> {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected String getCacheKey() {
        return CacheKey.PLAY_LIST;
    }

    private static String getPlayListSongsCacheKey(String id) {
        return String.format(CacheKey.PLAY_LIST_SONGS, id);
    }

    /**
     * 根据歌单id获取音乐列表
     *
     * @param id id
     * @return 音乐列表
     */
    public List<Song> getSongs(String id) {
        return (List<Song>) redisTemplate.opsForHash().values(getPlayListSongsCacheKey(id));
    }

    /**
     * 添加到歌单中
     *
     * @param playListId
     * @param song
     * @return
     */
    public boolean addSong(String playListId, Song song) {
        if (StringUtils.isEmpty(playListId)) {
            throw new MusicException("未指定歌单id");
        }
        if (song == null) {
            throw new MusicException("歌曲不能为空");
        }
        if (StringUtils.isEmpty(song.getSource())) {
            throw new MusicException("歌曲来源不能为空");
        }
        if (StringUtils.isEmpty(song.getId())) {
            throw new MusicException("歌曲的id不能为空");
        }
        song.setLastUpdateTimestamp(System.currentTimeMillis());
        redisTemplate.opsForHash().put(getPlayListSongsCacheKey(playListId), song.getSource() + "#" + song.getId(), song);
        return true;
    }

    /**
     * 移除
     */
    public boolean removeSong(String playListId, Song song) {
        if (StringUtils.isEmpty(playListId)) {
            throw new MusicException("未指定歌单id");
        }
        if (song == null) {
            throw new MusicException("歌曲不能为空");
        }
        if (StringUtils.isEmpty(song.getSource())) {
            throw new MusicException("歌曲来源不能为空");
        }
        if (StringUtils.isEmpty(song.getId())) {
            throw new MusicException("歌曲的id不能为空");
        }
        redisTemplate.opsForHash().delete(getPlayListSongsCacheKey(playListId), song.getSource() + "#" + song.getId());
        return true;
    }

    @Override
    public Boolean delete(String id) {
        redisTemplate.delete(getPlayListSongsCacheKey(id));
        return super.delete(id);
    }
}
