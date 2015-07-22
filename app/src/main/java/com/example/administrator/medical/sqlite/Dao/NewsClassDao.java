package com.example.administrator.medical.sqlite.Dao;

import android.content.Context;

import com.example.administrator.medical.pojo.NewsClassPojo;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by Administrator on 2015/7/18.
 */
public class NewsClassDao extends NewsBaseDao{

    private Dao<NewsClassPojo, Integer> dao;

    public NewsClassDao(Context context){
        super(context);
        try {
            dao = helpler.getDao(NewsClassPojo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(NewsClassPojo classPojo){
        try {
            dao.create(classPojo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<NewsClassPojo> queryAll(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteAll() {
        try {
            dao.queryRaw("delete from tb_class");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
