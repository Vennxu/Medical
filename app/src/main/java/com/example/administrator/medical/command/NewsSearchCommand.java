package com.example.administrator.medical.command;

import com.example.administrator.medical.pojo.NewsClassPojo;
import com.example.administrator.medical.utils.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2015/7/17.
 */
public class NewsSearchCommand extends BaseCommand{

    private static final String TAG = NewsSearchCommand.class.getSimpleName();

    private static final String URL = NewsCommandUrl.NEWS_SEARCH;

    private String page;
    private String limit;
    private String keyword;

    public NewsSearchCommand(){

    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    @Override
    public String paramWithUrl() {
        return URL + "?" + NewsCommandFeiled.PAGE + "=" + page + "&"
                + NewsCommandFeiled.LIMIT + "=" + limit + "&"
                + NewsCommandFeiled.KEYWORD + "=" + keyword;
    }

    @Override
    public Object getJson(String json) {
        String searchJson = getObjectJson(json);
        if (TextUtils.isEmpty(searchJson)){
            return null;
        }
        try{
            return new Gson().fromJson(searchJson, new TypeToken<List<NewsClassPojo>>() {}.getType());
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public String getTagName() {
        return TAG;
    }
}
