package com.dezhishen.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dezhishen.music.domain.SystemAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemAccountMapper extends BaseMapper<SystemAccount> {
    List<SystemAccount> selectSystemAccountByName(@Param("loginName") String loginName);
}
