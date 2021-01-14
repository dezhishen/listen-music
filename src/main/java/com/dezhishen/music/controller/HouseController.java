package com.dezhishen.music.controller;

import com.dezhishen.music.base.BaseController;
import com.dezhishen.music.base.RespEntity;
import com.dezhishen.music.domain.House;
import com.dezhishen.music.service.HouseService;
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
        return success(houseService.save(house));
    }

    @GetMapping("/select")
    public RespEntity<List<House>> list(House house) {
        return success(houseService.select(house));
    }

    @GetMapping("/{id}")
    public RespEntity<House> get(@PathVariable String id) {
        return success(houseService.get(id));
    }

    @DeleteMapping("/{id}")
    public RespEntity<Boolean> delete(@PathVariable String id) {
        return success(houseService.delete(id));
    }
}


