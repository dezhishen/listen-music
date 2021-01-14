package com.dezhishen.music.controller;

import com.dezhishen.music.base.BaseController;
import com.dezhishen.music.base.RespEntity;
import com.dezhishen.music.domain.MusicSource;
import com.dezhishen.music.service.MusicSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dezhishen
 */
@RestController
@RequestMapping("/music-source")
public class MusicSourceController extends BaseController {
    @Autowired
    private MusicSourceService musicSourceService;

    @GetMapping("/list")
    public RespEntity<List<MusicSource>> list() {
        return success(musicSourceService.list());
    }
}
