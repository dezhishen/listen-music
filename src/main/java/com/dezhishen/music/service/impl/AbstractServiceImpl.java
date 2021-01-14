package com.dezhishen.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dezhishen.music.base.BaseDomain;
import com.dezhishen.music.service.IBaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public abstract class AbstractServiceImpl<T> implements IBaseService<T> {
    protected abstract BaseMapper<T> mapper();

    @Override
    public T get(String id) {
        return mapper().selectById(id);
    }

    @Override
    public Boolean delete(String id) {
        return mapper().deleteById(id) > 1;
    }

    @Override
    public PageInfo<T> query(T t, Integer pageNum, Integer pageSize) {
        return query(t, pageNum, pageSize, null, true);
    }

    @Override
    public PageInfo<T> query(T t, Integer pageNum, Integer pageSize, String orderBy) {
        return query(t, pageNum, pageSize, orderBy, true);
    }

    @Override
    public PageInfo<T> query(T t, Integer pageNum, Integer pageSize, Boolean count) {
        return query(t, pageNum, pageSize, null, count);
    }

    @Override
    public PageInfo<T> query(T t, Integer pageNum, Integer pageSize, String orderBy, Boolean count) {
        return PageHelper.startPage(pageNum, pageSize, orderBy).setCount(count).doSelectPageInfo(
                () -> mapper().selectList(new QueryWrapper<>(t))
        );
    }

    @Override
    public List<T> select(T t) {
        return mapper().selectList(new QueryWrapper<>(t));
    }

    @Override
    public T update(T t) {
        mapper().updateById(t);
        return t;
    }

    @Override
    public T insert(T t) {
        mapper().insert(t);
        return t;
    }

    @Override
    public T save(T t) {
        if (t instanceof BaseDomain) {
            if (StringUtils.isEmpty(((BaseDomain) t).getId())) {
                return insert(t);
            } else {
                return update(t);
            }
        }
        throw new NotImplementedException();
    }

    @Override
    public List<T> selectAll() {
        return mapper().selectList(null);
    }
}
