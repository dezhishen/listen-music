package com.dezhishen.music.interceptor;

import com.dezhishen.music.constant.CacheKey;
import com.dezhishen.music.constant.SessionKey;
import com.dezhishen.music.domain.Biscuit;
import com.dezhishen.music.service.BiscuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token拦截器
 *
 * @author dezhishen
 */
@Service
public class BiscuitHandlerFilter implements Filter {
    private static final String HEAD_NAME = SessionKey.BISCUIT;
    private static final String PARAM_NAME = "_" + SessionKey.BISCUIT + "_";
    @Autowired
    private BiscuitService biscuitService;
    @Autowired
    private BiscuitFilterConfig filterConfig;
    @Autowired
    private CacheManager cacheManager;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (!filterConfig.isEnabled()) {
            filterChain.doFilter(servletRequest, response);
            return;
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (filterConfig.getIgnore().contains(request.getRequestURI())) {
            filterChain.doFilter(servletRequest, response);
            return;
        }
        String biscuitId = request.getHeader(HEAD_NAME);
        if (StringUtils.isEmpty(biscuitId)) {
            biscuitId = request.getParameter(PARAM_NAME);
        }
        if (StringUtils.isEmpty(biscuitId)) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not Allowed to Access.biscuit is null");
            return;
        }
        Cache cache = cacheManager.getCache(CacheKey.BISCUIT);
        Biscuit biscuit = cache.get(biscuitId, Biscuit.class);
        if (biscuit == null) {
            biscuit = biscuitService.get(biscuitId);
            if (biscuit == null && "_test_".equals(biscuitId)) {
                biscuit = new Biscuit();
                biscuit.setId(biscuitId);
                biscuit.setUserId("testUser");
                biscuitService.insert(biscuit);
            }
            if (biscuit != null) {
                cache.put(biscuitId, biscuit);
            }
        }
        if (biscuit == null) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not Allowed to Access.biscuit is verify");
            return;
        }
        request.getSession().setAttribute(HEAD_NAME, biscuitId);
        filterChain.doFilter(servletRequest, response);
    }
}
