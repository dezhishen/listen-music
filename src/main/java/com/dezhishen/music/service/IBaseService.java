package com.dezhishen.music.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBaseService<T> {
    /**
     * 获取单个
     *
     * @param id
     * @return
     */
    T get(String id);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    Boolean delete(String id);

    /**
     * 分页查询
     *
     * @param t
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<T> query(T t, Integer pageNum, Integer pageSize);

    /**
     * 分页查询
     *
     * @param t
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    PageInfo<T> query(T t, Integer pageNum, Integer pageSize, String orderBy);

    /**
     * 分页查询
     *
     * @param t
     * @param pageNum
     * @param pageSize
     * @param count
     * @return
     */
    PageInfo<T> query(T t, Integer pageNum, Integer pageSize, Boolean count);

    /**
     * 分页查询
     *
     * @param t
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @param count
     * @return
     */
    PageInfo<T> query(T t, Integer pageNum, Integer pageSize, String orderBy, Boolean count);

    /**
     * 不分页查询
     *
     * @param t
     * @return
     */
    List<T> select(T t);

    /**
     * 更新
     *
     * @param t
     * @return
     */
    T update(T t);

    /**
     * 插入
     *
     * @param t
     * @return
     */
    T insert(T t);

    /**
     * 更新或者插入
     * id?update:insert
     *
     * @param t
     * @return
     */
    T save(T t);

    List<T> selectAll();
}
