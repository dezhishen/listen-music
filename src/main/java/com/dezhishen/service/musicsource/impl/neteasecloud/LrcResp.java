package com.dezhishen.service.musicsource.impl.neteasecloud;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LrcResp {
    @Setter
    @Getter
    public static class Lrc {
        private String lyric;

        public Lrc() {
        }
    }

    private Lrc lrc;
}
