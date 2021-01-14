package com.dezhishen.music.service;

import com.dezhishen.music.domain.Song;

public interface SongService extends IBaseService<Song> {
    void insertAsync(Song result);
}
