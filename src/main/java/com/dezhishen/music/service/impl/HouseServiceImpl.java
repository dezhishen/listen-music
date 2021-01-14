package com.dezhishen.music.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dezhishen.music.domain.House;
import com.dezhishen.music.domain.SystemUser;
import com.dezhishen.music.mapper.HouseMapper;
import com.dezhishen.music.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dezhishen
 */
@Service
public class HouseServiceImpl extends AbstractServiceImpl<House> implements HouseService {
    @Autowired
    private HouseMapper mapper;

    @Override
    protected BaseMapper<House> mapper() {
        return null;
    }
}
