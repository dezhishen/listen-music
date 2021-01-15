package com.dezhishen.music.service.impl;

import cn.hutool.core.util.IdUtil;
import com.dezhishen.music.constant.CacheKey;
import com.dezhishen.music.domain.SystemAccount;
import com.dezhishen.music.dto.LoginResult;
import com.dezhishen.music.dto.TokenDto;
import com.dezhishen.music.exception.MusicException;
import com.dezhishen.music.service.TokenService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private CacheManager cacheManager;

    /**
     * 12小时后过期
     */
    private final static long EXPIRE = 7 * 24 * 60 * 60;


    /**
     * 根据token查询账号
     *
     * @param token
     * @return
     */
    @Override
    public SystemAccount queryAccountByToken(String token) {
        Cache cache = cacheManager.getCache(CacheKey.ACCESS_TOKEN);
        return cache.get(token, SystemAccount.class);
    }

    @Override
    public void removeToken(String token) {
        Cache cache = cacheManager.getCache(CacheKey.ACCESS_TOKEN);
        SystemAccount account = cache.get(token, SystemAccount.class);
        if (account != null) {
            Cache accountTokenCache = cacheManager.getCache(CacheKey.ACCOUNT_ACCESS_TOKEN);
            TokenDto tokenDto = accountTokenCache.get(account.getId(), TokenDto.class);
            if (tokenDto != null) {
                cache.evict(token);
                Cache refreshCache = cacheManager.getCache(CacheKey.REFRESH_TOKEN);
                refreshCache.evict(tokenDto.getRefreshToken());
                accountTokenCache.evict(account.getId());
            }
        }
    }

    /**
     * 生成token
     *
     * @param account
     * @return
     */
    @Override
    public LoginResult createToken(SystemAccount account) {
        Cache accountTokenCache = cacheManager.getCache(CacheKey.ACCOUNT_ACCESS_TOKEN);
        Cache tokenCache = cacheManager.getCache(CacheKey.ACCESS_TOKEN);
        Cache refreshTokenCache = cacheManager.getCache(CacheKey.REFRESH_TOKEN);
        TokenDto tokenDto = accountTokenCache.get(account.getId(), TokenDto.class);
        LoginResult result = new LoginResult();
        result.setUserId(account.getUserId());
        result.setExpire(EXPIRE);
        String token;
        String refreshToken;
        if (tokenDto != null) {
            token = tokenDto.getToken();
            refreshToken = tokenDto.getRefreshToken();
        } else {
            tokenDto = new TokenDto();
            token = IdUtil.fastSimpleUUID();
            refreshToken = IdUtil.fastSimpleUUID();
        }
        if (StringUtils.isEmpty(token)) {
            token = IdUtil.fastSimpleUUID();
        }
        if (StringUtils.isEmpty(refreshToken)) {
            refreshToken = IdUtil.fastSimpleUUID();
        }
        result.setToken(token);
        result.setRefreshToken(refreshToken);
        tokenDto.setToken(token);
        tokenDto.setRefreshToken(refreshToken);
        tokenCache.put(token, account);
        refreshTokenCache.put(refreshToken, account);
        accountTokenCache.put(account.getId(), tokenDto);
        return result;
    }


    @Override
    public LoginResult refreshToken(String refreshToken) {
        Cache refreshTokenCache = cacheManager.getCache(CacheKey.REFRESH_TOKEN);
        SystemAccount account = refreshTokenCache.get(refreshToken, SystemAccount.class);
        if (account == null) {
            throw new MusicException(401, "token verify error");
        }
        Cache accountTokenCache = cacheManager.getCache(CacheKey.ACCOUNT_ACCESS_TOKEN);
        Cache tokenCache = cacheManager.getCache(CacheKey.ACCESS_TOKEN);
        TokenDto tokenDto = accountTokenCache.get(account.getId(), TokenDto.class);
        if (tokenDto == null) {
            tokenDto = new TokenDto();
            tokenDto.setToken(IdUtil.fastSimpleUUID());
        }
        tokenDto.setRefreshToken(refreshToken);
        LoginResult result = new LoginResult();
        result.setUserId(account.getUserId());
        result.setExpire(EXPIRE);
        result.setRefreshToken(refreshToken);
        result.setToken(tokenDto.getToken());
        tokenCache.put(tokenDto.getToken(), account);
        accountTokenCache.put(account.getId(), tokenDto);
        refreshTokenCache.put(refreshToken, account);
        return result;
    }
}
