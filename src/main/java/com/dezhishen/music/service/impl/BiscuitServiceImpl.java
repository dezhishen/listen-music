package com.dezhishen.music.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dezhishen.music.domain.Biscuit;
import com.dezhishen.music.mapper.BiscuitMapper;
import com.dezhishen.music.service.BiscuitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BiscuitServiceImpl extends AbstractServiceImpl<Biscuit> implements BiscuitService {
    @Autowired
    private BiscuitMapper mapper;

    @Override
    protected BaseMapper<Biscuit> mapper() {
        return mapper;
    }
}
