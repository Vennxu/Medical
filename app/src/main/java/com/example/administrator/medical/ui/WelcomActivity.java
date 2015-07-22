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
public class WelcomActivity extends Activity implements Handler.Callback{

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
            public void onReulst(boolean succes, Object result) {
                handler.obtainMessage(succes ? LOAD_SUCCES : LOAD_FAILED, result).sendToTarget();
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
                }
                break;
            case LOAD_FAILED:
                break;
            default:
                handle = true;
                break;
        }
        return handle;
    }
}
