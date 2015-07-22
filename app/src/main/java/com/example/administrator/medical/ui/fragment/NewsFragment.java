package com.example.administrator.medical.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;

import com.example.administrator.medical.R;
import com.example.administrator.medical.http.NewsHttpManager;
import com.example.administrator.medical.http.NewsResultListener;
import com.example.administrator.medical.pojo.NewsClassPojo;
import com.example.administrator.medical.pojo.NewsListPolo;
import com.example.administrator.medical.ui.NewsDescriptionActivity;
import com.example.administrator.medical.ui.adapter.NewsAdapter;
import com.example.administrator.medical.ui.adapter.RecyclerItemClickerListener;
import com.example.administrator.medical.ui.widget.EmptyLayout;
import com.example.administrator.medical.ui.widget.EmptyRecyclerView;

import java.util.ArrayList;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2015/7/21.
 */
public class NewsFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate, Handler.Callback {

    public static final String NEWS_LIST = "newslist";

    private static final int LOAD_SUCCES = 101;
    private static final int LOAD_FAILED = 102;

    private BGARefreshLayout mRefreshLayout;
    private EmptyRecyclerView mRecyclerView;
    private EmptyLayout emptyLayout;
    private NewsAdapter mAdapter;
    private NewsHttpManager mHttpManager;
    private NewsClassPojo mNewsClassPojo;
    private int mRefreshPage = 1;
    private int mMorePage = 1;
    private boolean mIsRefresh;
    private Context mContext;

    private Handler mHandler = new Handler(this);

    public static NewsFragment newInstance(NewsClassPojo classPojo) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(NewsFragment.NEWS_LIST, classPojo);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mAdapter = new NewsAdapter(getActivity(), new RecyclerItemClickerListener() {
            @Override
            public void onItemClick(int position) {
                NewsListPolo newsListPojo = mAdapter.getItem(position);
                Intent intent = new Intent(mContext, NewsDescriptionActivity.class);
                intent.putExtra(NewsDescriptionActivity.DETAIL, newsListPojo);
                startActivity(intent);
            }
        });
        mHttpManager = NewsHttpManager.getInstance(getActivity());
        getArgment();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_news);
        mRefreshLayout = getViewById(R.id.news_refresh);
        mRecyclerView = getViewById(R.id.news_recycler);
        emptyLayout = new EmptyLayout(getActivity(), mRecyclerView);
        mRefreshLayout.setDelegate(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        onLoadData();
    }

    private void getArgment() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mNewsClassPojo = bundle.getParcelable(NEWS_LIST);
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        mIsRefresh = true;
        onLoadData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        if (mAdapter.getItemCount() % 2 == 20) {
            mIsRefresh = false;
            onLoadData();
        }
        return false;
    }

    private void onLoadData() {
        if (mNewsClassPojo == null) {
            return;
        }
        mHttpManager.getNewsList(mNewsClassPojo.getId(), mIsRefresh ? mRefreshPage : mMorePage, "20", "id", new NewsResultListener() {
            @Override
            public void onReulst(boolean succes, Object result) {
                mHandler.obtainMessage(succes ? LOAD_SUCCES : LOAD_FAILED, result).sendToTarget();
            }
        });
    }

    @Override
    public boolean handleMessage(Message msg) {
        boolean handle = true;
        switch (msg.what) {
            case LOAD_SUCCES:
                onHandlerLoadResult(msg);
                break;
            case LOAD_FAILED:
                mAdapter.clear();
                emptyLayout.showError();
                showToast("加载失败");
                break;
            default:
                handle = false;
                break;
        }
        return handle;
    }

    private void onHandlerLoadResult(Message msg) {
        ArrayList<NewsListPolo> pojos = (ArrayList<NewsListPolo>) msg.obj;
        if (pojos != null) {
            if (mIsRefresh) {
                mAdapter.addRefreshData(pojos);
            } else {
                mMorePage++;
                mAdapter.addMoreData(pojos);
            }
        }
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void onUserVisible() {

    }
}
