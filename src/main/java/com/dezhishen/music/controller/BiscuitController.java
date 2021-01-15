package com.dezhishen.music.controller;

import com.dezhishen.music.base.BaseController;
import com.dezhishen.music.base.RespEntity;
import com.dezhishen.music.domain.Biscuit;
import com.dezhishen.music.service.BiscuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 饼干
 *
 * @author dezhishen
 */
@RestController
@RequestMapping("/biscuit")
@Deprecated
public class BiscuitController extends BaseController {
    @Autowired
    private BiscuitService biscuitService;

    @PostMapping("/save")
    public RespEntity<Biscuit> save(@RequestBody Biscuit biscuit) {
        return success(biscuitService.save(biscuit));
    }
}
