package com.dezhishen.controller;

import com.dezhishen.base.BaseController;
import com.dezhishen.base.RespEntity;
import com.dezhishen.domain.House;
import com.dezhishen.domain.SystemUser;
import com.dezhishen.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController extends BaseController {
    @Autowired
    private HouseService houseService;

    @PostMapping("/create")
    public RespEntity<House> create(@RequestBody House house) {
        return success(houseService.create(house));
    }

    @GetMapping("/list")
    public RespEntity<List<House>> list(House house) {
        return success(houseService.list(house));
    }

    @GetMapping("/listAll")
    public RespEntity<List<House>> listAll() {
        return success(houseService.listAll());
    }

    @GetMapping("/get/{id}")
    public RespEntity<House> get(@PathVariable String id) {
        return success(houseService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public RespEntity<Boolean> delete(@PathVariable String id) {
        return success(houseService.delete(id));
    }

    @PostMapping("/join")
    public RespEntity<SystemUser> join(@RequestParam String houseId) {
        SystemUser user = getUser();
        user.setHouseId(houseId);
        return success(houseService.joinHouse(user));
    }

    @DeleteMapping("/leave")
    public RespEntity<Boolean> leave(@RequestParam String houseId) {
        return success(houseService.leaveHouse(houseId, getUser().getId()));
    }
}


