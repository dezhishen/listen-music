package com.dezhishen.base;

import com.dezhishen.domain.SystemUser;
import com.dezhishen.exception.MusicException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author dezhishen
 */
public class BaseController {
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
        return new SystemUser();
    }
}
