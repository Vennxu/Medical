package com.example.administrator.medical.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.administrator.medical.R;
import com.example.administrator.medical.http.NewsHttpManager;
import com.example.administrator.medical.http.NewsResultListener;
import com.example.administrator.medical.pojo.NewsClassPojo;
import com.example.administrator.medical.sqlite.Dao.NewsClassDao;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/7/22.
 */
public class WelcomeActivity extends Activity implements Handler.Callback{

    private static final int LOAD_SUCCES = 101;
    private static final int LOAD_FAILED = 102;

    private Handler handler = new Handler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        getLoadSubtitle();
    }

    private void getLoadSubtitle(){
        NewsHttpManager.getInstance(this).getNewsClass("1", getString(R.string.news_class), new NewsResultListener() {
            @Override
            public void onResult(boolean success, Object result) {
                handler.obtainMessage(success ? LOAD_SUCCES : LOAD_FAILED, result).sendToTarget();
            }
        });
    }

    @Override
    public boolean handleMessage(Message msg) {
        boolean handle = true;
        switch (msg.what){
            case LOAD_SUCCES:
                ArrayList<NewsClassPojo> classPojos = (ArrayList<NewsClassPojo>) msg.obj;
                if (classPojos != null) {
                    NewsClassDao newsClassDao = new NewsClassDao(this);
                    newsClassDao.deleteAll();
                    for (int i = 0; i < classPojos.size(); i++){
                        newsClassDao.add(classPojos.get(i));
                    }
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }else{
                    getLoadSubtitle();
                }
                break;
            case LOAD_FAILED:
                getLoadSubtitle();
                break;
            default:
                handle = true;
                break;
        }
        return handle;
    }
}
