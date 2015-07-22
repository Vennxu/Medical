package com.example.administrator.medical.sqlite.Dao;

import android.content.Context;

import com.example.administrator.medical.sqlite.NewsDataBaseHelpler;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by Administrator on 2015/7/18.
 */
public class NewsBaseDao {


    private Context context;
    public NewsDataBaseHelpler helpler;

    public NewsBaseDao(Context context) {
        this.context = context;
        helpler = NewsDataBaseHelpler.getInstanse(context);
    }


}
