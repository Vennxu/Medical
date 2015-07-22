package com.example.administrator.medical.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.medical.R;
import com.example.administrator.medical.http.NewsHttpManager;
import com.example.administrator.medical.http.NewsResultListener;
import com.example.administrator.medical.pojo.NewsListPolo;
import com.example.administrator.medical.pojo.NewsSearchPojo;
import com.example.administrator.medical.ui.adapter.NewsBaseAdapter;
import com.example.administrator.medical.ui.adapter.RecyclerItemClickerListener;
import com.example.administrator.medical.utils.TextUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/7/22.
 */
public class SearchActivity extends Activity implements View.OnClickListener, Handler.Callback{

    private static final int LOAD_SUCCES = 101;
    private static final int LOAD_FAILED = 102;

    private EditText mEidtText;
    private ImageView mSearchBtn;
    private RecyclerView mSearchList;
    private SearchAdapter mAdapter;
    private Handler handler = new Handler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mAdapter = new SearchAdapter(this, new RecyclerItemClickerListener() {
            @Override
            public void onItemClick(int position) {
                NewsSearchPojo searchPojo = mAdapter.getItem(position);
                Intent intent = new Intent(SearchActivity.this, NewsDescriptionActivity.class);
                intent.putExtra(NewsDescriptionActivity.DETAIL, searchPojo.getId());
                startActivity(intent);
            }
        });
        mEidtText = (EditText) findViewById(R.id.search_edit);
        mSearchBtn = (ImageView) findViewById(R.id.search_btn);
        mSearchList = (RecyclerView) findViewById(R.id.search_list);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSearchList.setLayoutManager(manager);
        mSearchList.setAdapter(mAdapter);
        mSearchBtn.setOnClickListener(this);
    }

    private void search(String keyword){
        NewsHttpManager.getInstance(this).getNewsSearch("1", "20", keyword, new NewsResultListener() {
            @Override
            public void onResult(boolean success, Object result) {
                handler.obtainMessage(success ? LOAD_SUCCES : LOAD_FAILED, result).sendToTarget();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_btn:
                String keyword = mEidtText.getText().toString();
                if (!TextUtils.isEmpty(keyword)){
                    search(keyword);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        boolean handle = true;
        switch (msg.what){
            case LOAD_SUCCES:
                ArrayList<NewsSearchPojo> newsSearchPojos = (ArrayList<NewsSearchPojo>) msg.obj;
                mAdapter.addRefreshData(newsSearchPojos);
                break;
            case LOAD_FAILED:
                break;
            default:
                handle = false;
                break;
        }
        return handle;
    }

    private class SearchAdapter extends NewsBaseAdapter{

        private ArrayList<NewsSearchPojo> searchPojos;
        private RecyclerItemClickerListener itemClickerListener;

        public SearchAdapter(Context context, RecyclerItemClickerListener itemClickerListener){
            super(context);
            this.itemClickerListener = itemClickerListener;
            searchPojos = new ArrayList<>();
        }

        public void addRefreshData(ArrayList<NewsSearchPojo> pojos) {
            if (searchPojos != null) {
                searchPojos.addAll(0, pojos);
                notifyItemRangeInserted(0, pojos.size());
            }
        }

        public void addMoreData(ArrayList<NewsSearchPojo> polos) {
            if (searchPojos != null) {
                searchPojos.addAll(searchPojos.size(), polos);
                notifyItemRangeInserted(searchPojos.size(), polos.size());
            }
        }

        public void clear(){
            if (searchPojos != null){
                searchPojos.clear();
                notifyDataSetChanged();
            }
        }

        @Override
        protected RecyclerView.ViewHolder initViewHolder(ViewGroup parent, int viewType) {
            return new NewSearchViewHolder(inflater.inflate(R.layout.item_search, parent, false));
        }

        @Override
        protected void bindData(RecyclerView.ViewHolder holder, int position) {
            NewSearchViewHolder viewHolder = (NewSearchViewHolder) holder;
            viewHolder.recenter(getItem(position));
        }

        public NewsSearchPojo getItem(int position){
            return searchPojos.get(position);
        }

        @Override
        protected int setDataSize() {
            return searchPojos.size();
        }

        private class NewSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private TextView textView;

            public NewSearchViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                textView = (TextView) itemView.findViewById(R.id.item_search_title);
            }

            public void recenter(NewsSearchPojo searchPojo){
                if (searchPojo != null){
                    String html = "<html><body>" + searchPojo.getTitle() + "</body></html>";
                    textView.setText(Html.fromHtml(html, null, null));
                }
            }

            @Override
            public void onClick(View v) {
                itemClickerListener.onItemClick(getPosition());
            }
        }

    }

}
