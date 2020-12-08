package com.dezhishen.service.impl;

import com.dezhishen.domain.House;
import com.dezhishen.domain.SystemUser;
import com.dezhishen.exception.MusicException;
import com.dezhishen.service.HouseService;
import com.dezhishen.storage.HouseStorage;
import com.dezhishen.storage.HouseUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dezhishen
 */
@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseStorage houseStorage;
    @Autowired
    private HouseUserStorage houseUserStorage;

    @Override
    public House create(House house) {
        if (StringUtils.isEmpty(house.getName())) {
            throw new MusicException("房间名为空");
        }
        return houseStorage.save(house);
    }

    @Override
    public boolean delete(String houseId) {
        return houseStorage.delete(houseId);
    }

    @Override
    public List<House> list(House condition) {
        List<House> all = this.listAll();
        if (all == null || all.isEmpty()) {
            return null;
        }
        return all.stream().filter(house -> !house.getName().contains(condition.getName())).collect(Collectors.toList());
    }

    @Override
    public List<House> listAll() {
        return houseStorage.listAll();
    }

    @Override
    public House get(String id) {
        return houseStorage.get(id);
    }

    @Override
    public List<SystemUser> listAllUser(String id) {
        return houseUserStorage.listAll(id);
    }

    @Override
    public SystemUser joinHouse(SystemUser user) {
        return houseUserStorage.join(user);
    }

    @Override
    public Boolean leaveHouse(String houseId, String userId) {
        return houseUserStorage.leave(houseId, userId);
    }
}
