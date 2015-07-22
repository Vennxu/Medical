package com.example.administrator.medical.utils;

/**
 * Created by Administrator on 2015/7/17.
 */
public class TextUtils {

    public static boolean isEmpty(String str) {
        boolean isEmpty = false;
        if (str == null || str.equals("")) {
            isEmpty = true;
        }
        return isEmpty;
    }

}
