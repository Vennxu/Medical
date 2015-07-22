package com.example.administrator.medical.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/7/21.
 */
public class DataUtils {

    public static String dateFormat(Date date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
       return format.format(date);
    }

}
