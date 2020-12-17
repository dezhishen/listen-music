package com.dezhishen.controller;

import com.dezhishen.base.BaseController;
import com.dezhishen.base.RespEntity;
import com.dezhishen.domain.Biscuit;
import com.dezhishen.service.BiscuitService;
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
public class BiscuitController extends BaseController {
    @Autowired
    private BiscuitService biscuitService;

    @PostMapping("/save")
    public RespEntity<Biscuit> save(@RequestBody Biscuit biscuit) {
        return success(biscuitService.save(biscuit));
    }
}
