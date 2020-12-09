package com.dezhishen.service;

import com.dezhishen.domain.SystemUser;

/**
 * @author dezhishen
 */
public interface SystemUserService {
    /**
     * 获取
     *
     * @param id
     * @return
     */
    SystemUser get(String id);

    /**
     * 创建
     *
     * @param biscuit
     * @return
     */
    SystemUser save(SystemUser systemUser);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean delete(String id);
}
