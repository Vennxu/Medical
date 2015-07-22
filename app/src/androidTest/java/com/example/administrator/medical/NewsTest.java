package com.example.administrator.medical;

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.example.administrator.medical.http.NewsHttpManager;
import com.example.administrator.medical.http.NewsResultListener;
import com.example.administrator.medical.pojo.NewsClassPojo;
import com.example.administrator.medical.sqlite.Dao.NewsClassDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2015/7/21.
 */
public class NewsTest extends InstrumentationTestCase{


    public void test(){
        NewsHttpManager httpManager = NewsHttpManager.getInstance(getInstrumentation().getTargetContext());
        httpManager.getNewsClass("1", "企业要闻", new NewsResultListener() {
            @Override
            public void onResult(boolean succes, Object result) {

            }
        });
    }

}
