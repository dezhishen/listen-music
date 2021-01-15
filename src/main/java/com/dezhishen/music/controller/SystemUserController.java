package com.dezhishen.music.controller;

import com.dezhishen.music.base.BaseController;
import com.dezhishen.music.base.RespEntity;
import com.dezhishen.music.domain.SystemUser;
import com.dezhishen.music.mapper.SystemUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/user")
public class SystemUserController extends BaseController {
    @Autowired
    private SystemUserMapper systemUserMapper;

    @GetMapping("current")
    public RespEntity<SystemUser> get() {
        return success(systemUserMapper.selectById(getUserId()));
    }
}
