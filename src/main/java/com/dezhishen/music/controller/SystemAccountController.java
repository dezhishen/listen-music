package com.dezhishen.music.controller;

import com.dezhishen.music.base.BaseController;
import com.dezhishen.music.base.RespEntity;
import com.dezhishen.music.domain.SystemAccount;
import com.dezhishen.music.dto.LoginRequest;
import com.dezhishen.music.dto.LoginResult;
import com.dezhishen.music.service.SystemAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/account")
public class SystemAccountController extends BaseController {
    @Autowired
    SystemAccountService systemAccountService;

    @PostMapping("/register")
    public RespEntity<Boolean> register(@RequestBody SystemAccount account) {
        return success(systemAccountService.registerAccount(account));
    }

    @PostMapping("/login")
    public RespEntity<LoginResult> login(@RequestParam String loginName, @RequestParam String password) {
        LoginRequest request = new LoginRequest();
        request.setLoginName(loginName);
        request.setPassword(password);
        return success(systemAccountService.login(request));
    }

    @DeleteMapping("/loginOut")
    public RespEntity<Boolean> loginOut() {
        return success(systemAccountService.loginOut(getToken()));
    }


}
