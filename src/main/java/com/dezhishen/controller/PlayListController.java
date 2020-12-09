package com.dezhishen.controller;

import com.dezhishen.base.BaseController;
import com.dezhishen.base.RespEntity;
import com.dezhishen.domain.PlayList;
import com.dezhishen.domain.Song;
import com.dezhishen.service.PlayListService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dezhishen
 */
@RestController
@RequestMapping("/play-list")
public class PlayListController extends BaseController {
    @Autowired
    private PlayListService playListService;

    @GetMapping("/get/{id}")
    public RespEntity<PlayList> get(@PathVariable String id) {
        return success(playListService.get(id));
    }

    @PostMapping("/save")
    public RespEntity<PlayList> save(@RequestBody PlayList playList) {
        return success(playListService.save(playList));
    }

    @DeleteMapping("/delete")
    public RespEntity<Boolean> delete(@RequestParam String id) {
        return success(playListService.delete(id));
    }

    @Getter
    @Setter
    private static class SongForAdd extends Song {
        private String playListId;
    }

    @PostMapping("/song/add")
    public RespEntity<Song> addSong(@RequestBody SongForAdd song) {
        return success(playListService.addSong(song.getPlayListId(), song));
    }

    @DeleteMapping("/song/remove")
    public RespEntity<Boolean> removeSong(@RequestParam String playListId, @RequestParam String songId, @RequestParam String source) {
        return success(playListService.removeSong(playListId, source, songId));
    }

    @GetMapping("/song/listAll")
    public RespEntity<List<Song>> listSongs(@RequestParam String playListId) {
        return success(playListService.listSongs(playListId));
    }
}
