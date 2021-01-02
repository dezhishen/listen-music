package com.dezhishen.controller;

import com.dezhishen.base.BaseController;
import com.dezhishen.base.RespEntity;
import com.dezhishen.service.MusicServiceLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/music-login")
public class MusicServiceLoginController extends BaseController {
    @Autowired
    private MusicServiceLoginService serviceLoginService;

    @GetMapping("/loginByPhone")
    public RespEntity<Boolean> loginByPhone(String source, String phone, String password) {
        return success(serviceLoginService.loginByPhone(source, phone, password));
    }
}
