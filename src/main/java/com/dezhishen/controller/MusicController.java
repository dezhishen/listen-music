package com.dezhishen.controller;

import com.dezhishen.base.BaseController;
import com.dezhishen.base.RespEntity;
import com.dezhishen.domain.Music;
import com.dezhishen.domain.MusicUser;
import com.dezhishen.domain.PlayList;
import com.dezhishen.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 音乐
 *
 * @author dezhishen
 */
@RestController
@RequestMapping("/music")
public class MusicController extends BaseController {
    @Autowired
    private MusicService musicService;

    @GetMapping("/search")
    public RespEntity<Page<Music>> search(
            @RequestParam String q,
            @RequestParam String source,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return success(musicService.searchMusic(q, source, pageNum, pageSize));
    }

    @GetMapping("/searchMusicUser")
    public RespEntity<Page<MusicUser>> searchMusicUser(
            @RequestParam String q,
            @RequestParam String source,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return success(musicService.searchMusicUser(q, source, pageNum, pageSize));
    }

    @PostMapping("/searchPlayList")
    public RespEntity<Page<PlayList>> searchPlayList(
            @RequestParam String q,
            @RequestParam String source,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return success(musicService.searchPlayList(q, source, pageNum, pageSize));

    }
}
