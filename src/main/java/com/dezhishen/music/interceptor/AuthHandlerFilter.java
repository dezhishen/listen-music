package com.dezhishen.music.interceptor;

import com.dezhishen.music.constant.SessionKey;
import com.dezhishen.music.domain.SystemAccount;
import com.dezhishen.music.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
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
public class AuthHandlerFilter implements Filter {
    private static final String TOKEN_HEAD_NAME = SessionKey.TOKEN;
    private static final String TOKEN_PARAM_NAME = "_" + SessionKey.TOKEN + "_";
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthFilterConfig filterConfig;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (!filterConfig.isEnabled()) {
            filterChain.doFilter(servletRequest, response);
            return;
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURI().replaceFirst(request.getContextPath(),"");
        if (filterConfig.getIgnore() != null) {
            boolean contains = false;
            PathMatcher matcher = new AntPathMatcher();
            for (String s : filterConfig.getIgnore()) {
                if (matcher.match(s, url)) {
                    contains = true;
                    break;
                }
            }
            if (contains) {
                filterChain.doFilter(servletRequest, response);
                return;
            }
        }
        String token = request.getHeader(TOKEN_HEAD_NAME);
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(TOKEN_PARAM_NAME);
        }
        if (StringUtils.isEmpty(token)) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not Allowed to Access.biscuit is null");
            return;
        }
        SystemAccount account = tokenService.queryAccountByToken(token);
        if (account == null) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not Allowed to Access.biscuit is verify");
            return;
        }
        request.getSession().setAttribute(TOKEN_HEAD_NAME, token);
        request.getSession().setAttribute(SessionKey.ACCOUNT_ID, account.getId());
        request.getSession().setAttribute(SessionKey.USER_ID, account.getUserId());
        filterChain.doFilter(servletRequest, response);
    }
}
