package com.example.administrator.medical.http;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import com.example.administrator.medical.command.BaseCommand;
import com.example.administrator.medical.command.NewsClasssCommand;
import com.example.administrator.medical.command.NewsDescriptionCommand;
import com.example.administrator.medical.command.NewsListCommand;
import com.example.administrator.medical.command.NewsSearchCommand;

/**
 * Created by Administrator on 2015/7/16.
 */
public class NewsHttpManager extends BaseHttpManager {

    public static NewsHttpManager mHttpManager = null;

    private HandlerThread mHandlerThread;
    private Handler mHandler;

    public static NewsHttpManager getInstance(Context context){
        if (mHttpManager == null){
            initInstance(context);
        }
        return mHttpManager;
    }

    public static synchronized void initInstance(Context context){
        if (mHttpManager == null){
            mHttpManager = new NewsHttpManager(context.getApplicationContext());
        }
    }

    public NewsHttpManager(Context context) {
        super(context);
        mHandler = new Handler(getLooper());
    }

    public void getNewsClass( String id, String name, NewsResultListener resultListener){
        final NewsClasssCommand command = new NewsClasssCommand();
        command.setId(id);
        command.setName(name);
        new NewsCallListener(mHandler, mHttpManager, resultListener) {
            @Override
            public BaseCommand doBackground() {
               return command;
            }

            @Override
            public void result(boolean succese, Object result) {
                mResultListener.onResult(succese, result);
            }
        }.call();
    }

    public void getNewsList(String id, int page, String limit, String type, NewsResultListener resultListener){
        final NewsListCommand command = new NewsListCommand();
        command.setId(id);
        command.setPage(String.valueOf(page));
        command.setLimit(limit);
        command.setType(type);
        new NewsCallListener(mHandler, mHttpManager, resultListener){

            @Override
            public BaseCommand doBackground() {
                return command;
            }

            @Override
            public void result(boolean succese, Object result) {
                mResultListener.onResult(succese, result);
            }
        }.call();
    }

    public void getNewsDetail(String id, NewsResultListener resultListener){
        final NewsDescriptionCommand command = new NewsDescriptionCommand(id);
        new NewsCallListener(mHandler, mHttpManager, resultListener){

            @Override
            public BaseCommand doBackground() {
                return command;
            }

            @Override
            public void result(boolean succese, Object result) {
                mResultListener.onResult(succese, result);
            }
        }.call();
    }

    public void getNewsSearch(String page, String limit, String keyword, NewsResultListener resultListener){
        final NewsSearchCommand command = new NewsSearchCommand();
        command.setPage(page);
        command.setLimit(limit);
        command.setKeyword(keyword);
        new NewsCallListener(mHandler, mHttpManager, resultListener){

            @Override
            public BaseCommand doBackground() {
                return command;
            }

            @Override
            public void result(boolean succese, Object result) {
                mResultListener.onResult(succese, result);
            }
        }.call();
    }
}
