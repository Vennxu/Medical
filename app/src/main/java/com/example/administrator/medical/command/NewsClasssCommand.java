package com.example.administrator.medical.command;

import com.example.administrator.medical.pojo.NewsClassPojo;
import com.example.administrator.medical.utils.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/17.
 */
public class NewsClasssCommand extends BaseCommand{

    private static final String TAG = NewsClasssCommand.class.getSimpleName();
    private static final String URL = NewsCommandUrl.NEWS_CLASS;

    private String id;
    private String name;

    public NewsClasssCommand(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String paramWithUrl() {
        return URL+ "?" + NewsCommandFeiled.ID + "=" + id + "&" + NewsCommandFeiled.NAME + "=" + name;
    }

    @Override
    public Object getJson(String json) {
        String classJson = getObjectJson(json);
        if (TextUtils.isEmpty(classJson)){
            return null;
        }
        try{
            return new Gson().fromJson(classJson, new TypeToken<List<NewsClassPojo>>() {}.getType());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public String getTagName() {
        return TAG;
    }
}
