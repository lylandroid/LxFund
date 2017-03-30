package com.framework.listener;

import android.support.v4.widget.SwipeRefreshLayout;

import com.framework.base.adapter.LoadingMoreRecyclerAdapter;

/**
 * Created by lyl on 2016/10/7.
 */

public abstract class LoadingMoreListener implements SwipeRefreshLayout.OnRefreshListener {
    /**
     * 加载更多
     */
    public abstract void onRefreshLoadMore(LoadingMoreRecyclerAdapter.LoadMoreViewHolder holder);

    public void onRefresh() {
    }
}