package com.dezhishen.service.musicsource.impl.neteasecloud;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchSongResp {
    private int code;
    private NeteaseCloudMusics result;

    public SearchSongResp() {
    }
}
