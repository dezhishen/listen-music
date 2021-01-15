package com.dezhishen.music.controller;

import com.dezhishen.music.base.BaseController;
import com.dezhishen.music.base.RespEntity;
import com.dezhishen.music.domain.SystemAccount;
import com.dezhishen.music.service.SystemAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/account")
public class SystemAccountController extends BaseController {
    @Autowired
    SystemAccountService systemAccountService;

    @PostMapping("/register")
    public RespEntity<Boolean> register(@RequestBody SystemAccount account) {
        return success(systemAccountService.registerAccount(account));
    }


}
