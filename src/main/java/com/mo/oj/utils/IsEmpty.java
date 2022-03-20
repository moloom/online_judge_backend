package com.mo.oj.utils;


public class IsEmpty {

    /**
     * 判断字符串是否为null或空串
     * 是则返回true，否则false
     *
     * @param str
     * @return
     */
    public static boolean isEmptyOfString(String str) {
        if (str != null && !str.trim().equals(""))
            return false;
        else
            return true;
    }
}
