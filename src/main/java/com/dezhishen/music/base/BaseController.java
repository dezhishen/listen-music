package com.dezhishen.music.base;

import com.dezhishen.music.constant.SessionKey;
import com.dezhishen.music.domain.Biscuit;
import com.dezhishen.music.domain.SystemAccount;
import com.dezhishen.music.domain.SystemUser;
import com.dezhishen.music.exception.MusicException;
import com.dezhishen.music.service.SystemAccountService;
import com.dezhishen.music.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dezhishen
 */
public class BaseController {
    //    @Autowired
//    private BiscuitService _biscuitService;
    @Autowired
    private SystemUserService userService;
    @Autowired
    private SystemAccountService accountService;

    /**
     * 成功
     *
     * @param data 数据
     * @param <T>  泛型
     * @return 返回包装类对象
     */
    protected static <T> RespEntity<T> success(T data) {
        return new RespEntity<T>(data);
    }

    /**
     * 失败
     *
     * @param code    错误码
     * @param message 错误消息
     * @param <T>     泛型
     * @return 返回包装类对象
     */
    protected static <T> RespEntity<T> fail(int code, String message) {
        return new RespEntity<T>(code, message);
    }

    /**
     * 普通错误
     *
     * @param message
     * @param <T>
     * @return
     */
    protected static <T> RespEntity<T> fail(String message) {
        return fail(RespCode.PARAM_ERROR, message);
    }

    /**
     * 异常处理
     *
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler
    private <T> RespEntity<T> exceptionHandle(Exception e) {
        if (e instanceof MusicException) {
            e.printStackTrace();
            return fail(((MusicException) e).getCode(), e.getMessage());
        }
        e.printStackTrace();
        return fail(RespCode.EXCEPTION, e.getMessage());
    }

    /**
     * 获取当前会话的用户
     *
     * @return
     */
    protected SystemUser getUser() {
        String id = getUserId();
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        return userService.get(id);
    }

    protected String getUserId() {
        if (RequestContextHolder.getRequestAttributes() == null) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (StringUtils.isEmpty(request.getSession().getAttribute(SessionKey.USER_ID))) {
            return null;
        }
        return (String) request.getSession().getAttribute(SessionKey.USER_ID);
    }


    protected SystemAccount getAccount() {
        String id = getAccountId();
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        return accountService.get(id);
    }

    //
    protected String getAccountId() {
        if (RequestContextHolder.getRequestAttributes() == null) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (StringUtils.isEmpty(request.getSession().getAttribute(SessionKey.ACCOUNT_ID))) {
            return null;
        }
        return (String) request.getSession().getAttribute(SessionKey.ACCOUNT_ID);
    }

    protected String getToken() {
        if (RequestContextHolder.getRequestAttributes() == null) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (StringUtils.isEmpty(request.getSession().getAttribute(SessionKey.TOKEN))) {
            return null;
        }
        return (String) request.getSession().getAttribute(SessionKey.TOKEN);
    }

}
