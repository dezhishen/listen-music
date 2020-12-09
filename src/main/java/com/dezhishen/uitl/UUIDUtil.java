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

    /**
     * 生成16位的id
     *
     * @return
     */
    public static String genShortUUID() {
        String uuid = UUID.randomUUID().toString();
        char[] cs = new char[32];
        char c = 0;
        for (int i = uuid.length() / 2, j = 1; i-- > 0; ) {
            if ((c = uuid.charAt(i)) != '-') {
                cs[j++] = c;
            }
        }
        return String.valueOf(cs);
    }
}
