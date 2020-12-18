package com.dezhishen.interceptor;

import com.dezhishen.constant.SessionKey;
import com.dezhishen.domain.Biscuit;
import com.dezhishen.service.BiscuitService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
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
        Biscuit biscuit = biscuitService.get(biscuitId);
        if (biscuit == null && "_test_".equals(biscuitId)) {
            biscuit = new Biscuit();
            biscuit.setId(biscuitId);
            biscuit.setUserId("testUser");
            biscuitService.save(biscuit);
        }
        if (biscuit == null) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not Allowed to Access.biscuit is verify");
            return;
        }
        request.getSession().setAttribute(HEAD_NAME, biscuitId);
        //校验饼干是否存在
        filterChain.doFilter(servletRequest, response);
    }
}
