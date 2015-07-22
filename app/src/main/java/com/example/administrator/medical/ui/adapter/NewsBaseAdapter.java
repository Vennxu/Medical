package com.example.administrator.medical.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.administrator.medical.imageloader.NewImageDisplay;

/**
 * Created by Administrator on 2015/7/21.
 */
public abstract class NewsBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected LayoutInflater inflater;
    protected NewImageDisplay imageDisplay;

    public NewsBaseAdapter(Context context){
        inflater = LayoutInflater.from(context);
        imageDisplay = NewImageDisplay.getInstance(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return initViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindData(holder, position);
    }

    @Override
    public int getItemCount() {
        return setDataSize();
    }

    protected abstract RecyclerView.ViewHolder initViewHolder(ViewGroup parent, int viewType);

    protected abstract void bindData(RecyclerView.ViewHolder holder, int position);

    protected abstract int setDataSize();
}
