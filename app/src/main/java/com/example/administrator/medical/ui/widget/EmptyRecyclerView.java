package com.example.administrator.medical.ui.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2015/7/21.
 */

public class EmptyRecyclerView extends RecyclerView {

    private View mEmptyView;

    final private AdapterDataObserver mAdapterDataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            setEmptyViewVisible();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            setEmptyViewVisible();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            setEmptyViewVisible();
        }
    };

    public EmptyRecyclerView(Context context) {
        super(context);
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    void setEmptyViewVisible() {
        if (mEmptyView != null && getAdapter() != null) {
            boolean isEmptyViewVisible = getAdapter().getItemCount() == 0;
            mEmptyView.setVisibility(isEmptyViewVisible ? VISIBLE : GONE);
            setVisibility(isEmptyViewVisible ? GONE : VISIBLE);
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(mAdapterDataObserver);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(mAdapterDataObserver);
        }
        setEmptyViewVisible();
    }

    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
        setEmptyViewVisible();
    }
}
