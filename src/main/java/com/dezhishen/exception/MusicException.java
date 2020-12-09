package com.dezhishen.exception;

import com.dezhishen.base.RespCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 参数错误异常
 *
 * @author dezhishen
 */
@Setter
@Getter
public class MusicException extends RuntimeException {
    private String message;
    private int code;

    public MusicException(String message) {
        this(RespCode.PARAM_ERROR, message);
    }

    public MusicException(String message, String... args) {
        this(String.format(message, args));
    }


    public MusicException(int code, String message, String... args) {
        this(code, String.format(message, args));
    }

    public MusicException(int code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
