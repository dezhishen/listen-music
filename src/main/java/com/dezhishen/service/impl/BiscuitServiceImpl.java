package com.dezhishen.service.impl;

import com.dezhishen.domain.Biscuit;
import com.dezhishen.service.BiscuitService;
import com.dezhishen.storage.BiscuitStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BiscuitServiceImpl implements BiscuitService {
    @Autowired
    private BiscuitStorage biscuitStorage;

    @Override
    public Biscuit get(String id) {
        return biscuitStorage.get(id);
    }

    @Override
    public Biscuit save(Biscuit biscuit) {
        return biscuitStorage.save(biscuit);
    }

    @Override
    public boolean delete(String id) {
        return biscuitStorage.delete(id);
    }
}
