package com.dezhishen.music.uitl;

import cn.hutool.crypto.SecureUtil;

/**
 * 密码加密工具类
 */
public class PasswordUtil {

    /**
     * 密码是否一致
     *
     * @param loginName     登录名
     * @param salt          加密盐
     * @param inputPassword 输入的密码
     * @param dbPassword    加密后的密码
     * @return
     */
    public static boolean match(String loginName, String salt, String inputPassword, String dbPassword) {
        return dbPassword.equals(encryptPassword(loginName, salt, inputPassword));
    }

    /**
     * 加密密码
     *
     * @param loginName 登录名
     * @param salt      加密盐
     * @param password  要加密的密码
     * @return 加密后的密码
     */
    public static String encryptPassword(String loginName, String salt, String password) {
        return SecureUtil.md5(loginName + password + salt);
    }
}
