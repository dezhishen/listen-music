package com.dezhishen.service.musicsource.impl.neteasecloud;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 音乐详情返回结果
 */
@Setter
@Getter
public class NeteaseCloudMusics {
    private List<NeteaseCloudSong> songs;
    private Long songCount;
    private Boolean hasMore;

    public NeteaseCloudMusics() {
    }
}