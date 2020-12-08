package com.dezhishen.service;

import com.dezhishen.domain.House;
import com.dezhishen.domain.SystemUser;

import java.util.List;

/**
 * 房间服务
 *
 * @author dezhishen
 */
public interface HouseService {
    /**
     * 创建房间
     *
     * @param house 要创建的房间信息
     * @return 创建好的房间信息
     */
    House create(House house);

    /**
     * 解散房间
     *
     * @param houseId 房间号
     * @return 结果
     */
    boolean delete(String houseId);

    /**
     * 获取房间列表
     *
     * @param condition 条件,当前只处理name
     * @return 所有的房间
     */
    List<House> list(House condition);

    /**
     * 获取房间列表
     *
     * @return 所有的房间
     */
    List<House> listAll();

    /**
     * 获取单个房间信息
     *
     * @param id
     */
    House get(String id);

    /**
     * 抓取房间内的用户
     *
     * @param id 房间id
     * @return
     */
    List<SystemUser> listAllUser(String id);

    /**
     * 加入房间
     *
     * @param user
     * @return
     */
    SystemUser joinHouse(SystemUser user);

    /**
     * 离开房间
     *
     * @param houseId
     * @param userId
     * @return
     */
    Boolean leaveHouse(String houseId, String userId);
}
