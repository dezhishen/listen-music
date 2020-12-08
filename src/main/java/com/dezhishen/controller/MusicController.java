package com.dezhishen.controller;

import com.dezhishen.base.BaseController;
import com.dezhishen.base.RespEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 音乐
 *
 * @author dezhishen
 */
@RestController
@RequestMapping("/music")
public class MusicController extends BaseController {
    @GetMapping("/search")
    public RespEntity<?> search() {
        return success(null);
    }

}
