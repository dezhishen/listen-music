package com.dezhishen.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dezhishen.music.domain.SystemUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemUserMapper extends BaseMapper<SystemUser> {
    List<SystemUser> selectSystemUserByName(@Param("name") String name);
}
