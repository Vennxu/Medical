package com.example.administrator.medical.command;

import com.example.administrator.medical.pojo.NewsClassPojo;
import com.example.administrator.medical.pojo.NewsDecriptionPojo;
import com.example.administrator.medical.utils.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2015/7/17.
 */
public class NewsDescriptionCommand extends BaseCommand{

    private static final String TAG = NewsDescriptionCommand.class.getSimpleName();
    private static final String NEWS_DESCRIPTION_URL = NewsCommandUrl.NEWS_DETAIL;

    private String id; //新闻资讯ID标号

    public NewsDescriptionCommand(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String paramWithUrl() {
        return NEWS_DESCRIPTION_URL + "?" + NewsCommandFeiled.ID + "=" + id;
    }

    @Override
    public Object getJson(String json) {
        String detailJson = getObjectJson(json);
        if (TextUtils.isEmpty(detailJson)){
            return null;
        }
        try{
            return new Gson().fromJson(detailJson, NewsDecriptionPojo.class);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public String getTagName() {
        return TAG;
    }
}
