package com.dezhishen.service;

import com.dezhishen.domain.Biscuit;

/**
 * 饼干管理
 *
 * @author dezhishen
 */
public interface BiscuitService {
    /**
     * 获取
     *
     * @param id
     * @return
     */
    Biscuit get(String id);

    /**
     * 创建
     *
     * @param biscuit
     * @return
     */
    Biscuit save(Biscuit biscuit);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean delete(String id);
}
