package com.dezhishen.base;

import com.dezhishen.constant.SessionKey;
import com.dezhishen.domain.Biscuit;
import com.dezhishen.domain.SystemUser;
import com.dezhishen.exception.MusicException;
import com.dezhishen.service.BiscuitService;
import com.dezhishen.service.SystemUserService;
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
    @Autowired
    private BiscuitService _biscuitService;
    @Autowired
    private SystemUserService systemUserService;

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
        Biscuit biscuit = getBiscuit();
        if (biscuit == null) {
            return null;
        }
        return systemUserService.get(biscuit.getUserId());
    }

    protected String getUserId() {
        Biscuit biscuit = getBiscuit();
        if (biscuit == null) {
            return null;
        }
        return biscuit.getUserId();
    }

    protected Biscuit getBiscuit() {
        if (RequestContextHolder.getRequestAttributes() == null) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (StringUtils.isEmpty(request.getSession().getAttribute(SessionKey.BISCUIT))) {
            return null;
        }
        return _biscuitService.get((String) request.getSession().getAttribute(SessionKey.BISCUIT));
    }

}
