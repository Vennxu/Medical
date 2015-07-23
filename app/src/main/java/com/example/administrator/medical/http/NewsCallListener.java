package com.example.administrator.medical.http;

import android.os.Handler;
import android.util.Log;

import com.example.administrator.medical.command.BaseCommand;
import com.example.administrator.medical.utils.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Administrator on 2015/7/16.
 */
public abstract class NewsCallListener {

    private Handler handler;
    private NewsHttpManager httpManager;
    protected NewsResultListener mResultListener;
    private BaseCommand mBaseCommand;

    public NewsCallListener(Handler handler, NewsHttpManager httpManager, NewsResultListener resultListener) {
        this.handler = handler;
        this.httpManager = httpManager;
        mResultListener = resultListener;
    }

    public final void call() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                mBaseCommand = doBackground();
                dispose();
            }
        });
    }

    public void dispose() {
        boolean success = true;
        String pose = null;
        try {
            pose = httpManager.get(mBaseCommand.paramWithUrl());
            Log.d(mBaseCommand.getTagName(), mBaseCommand.paramWithUrl());
            Log.d(mBaseCommand.getTagName(), pose);
            if (TextUtils.isEmpty(pose)){
                result(success, null);
                return;
            }
            JSONObject jsonObject = new JSONObject(pose);
            if (jsonObject.has("success")) {
                success = jsonObject.getBoolean("success");
                result(success, mBaseCommand.getJson(pose));
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        result(success, null);
    }

    public abstract BaseCommand doBackground();

    public abstract void result(boolean success, Object result);

}
