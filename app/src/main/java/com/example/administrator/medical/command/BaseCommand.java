package com.example.administrator.medical.command;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/7/16.
 */
public abstract class BaseCommand {


    public BaseCommand() {

    }

    protected String getObjectJson(String json) {
        JSONObject jsonObject;
        String classJson = null;
        try {
            jsonObject = new JSONObject(json);
            classJson = jsonObject.getString(NewsCommandFeiled.JSON_YI);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return classJson;
    }

    public abstract String paramWithUrl();

    public abstract Object getJson(String json);

    public abstract String getTagName();
}
