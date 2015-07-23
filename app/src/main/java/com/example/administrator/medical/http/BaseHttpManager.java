package com.example.administrator.medical.http;

import android.content.Context;
import android.os.HandlerThread;
import android.os.Looper;

import com.example.administrator.medical.command.NewsCommandFeiled;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2015/7/16.
 */
public class BaseHttpManager{


    public static final String APIKEY = "002ed33b29e5b8b7a59f9d294bce3102";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private HandlerThread mHandlerThread;
    private Looper mLooper;

    private OkHttpClient mClient = null;


    public BaseHttpManager(Context context){
        mHandlerThread = new HandlerThread("BaseHttpManager");
        mHandlerThread.start();
        mLooper = mHandlerThread.getLooper();
        mClient = new OkHttpClient();
    }

    protected String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = mClient.newCall(request).execute();
        return response.body().string();
    }

    protected String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader(NewsCommandFeiled.API_KEY, APIKEY)
                .get()
                .build();
        Response response = mClient.newCall(request).execute();
        return response.body().string();
    }

    public Looper getLooper() {
        return mLooper;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        mHandlerThread.getLooper().quit();
        mHandlerThread.quit();
    }
}
