package com.dezhishen.uitl;

import java.util.UUID;

/**
 * UUID
 *
 * @author dezhishen
 */
public class UUIDUtil {
    /**
     * 生成uuid
     *
     * @return
     */
    public static String genUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
