package com.dezhishen.service.musicsource.impl.neteasecloud;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NeteaseCloudUrlResp {
    private List<NeteaseCloudUrl> data;

    public NeteaseCloudUrlResp() {
    }
}