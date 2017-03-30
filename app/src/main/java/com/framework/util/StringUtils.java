package com.framework.util;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/3/17.
 */

public class StringUtils {

    private final static Pattern URL = Pattern
            .compile("^(https|http)://.*?$(net|com|.com.cn|org|me|)");

    public static boolean isUrl(String str) {
        if (str == null || str.trim().length() == 0)
            return false;
        return URL.matcher(str).matches();
    }
}
