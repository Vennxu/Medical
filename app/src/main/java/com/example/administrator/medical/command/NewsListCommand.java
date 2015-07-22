package com.example.administrator.medical.command;

import com.example.administrator.medical.pojo.NewsListPolo;
import com.example.administrator.medical.utils.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2015/7/17.
 */
public class NewsListCommand extends BaseCommand {

    private static final String TAG = NewsListCommand.class.getSimpleName();
    private static final String NEWS_LIST_URL = NewsCommandUrl.NEWS_LIST;

    private String id;
    private String type;
    private String limit;
    private String page;

    public NewsListCommand() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public String paramWithUrl() {
        return NEWS_LIST_URL + "?"
                + NewsCommandFeiled.PAGE + "=" + page +"&"
                + NewsCommandFeiled.LIMIT + "=" + limit +"&"
                + NewsCommandFeiled.TYPE + "=" + type +"&"
                + NewsCommandFeiled.ID + "=" + id;
    }

    @Override
    public Object getJson(String json) {
        String objectJson = getObjectJson(json);
        if (TextUtils.isEmpty(objectJson)) {
            return null;
        }
        try{
            return new Gson().fromJson(objectJson, new TypeToken<List<NewsListPolo>>() {}.getType());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public String getTagName() {
        return TAG;
    }
}
