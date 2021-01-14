package com.dezhishen.music.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PlayListSong implements Serializable {
    private String songId;
    private String source;
    private String playListId;
}
