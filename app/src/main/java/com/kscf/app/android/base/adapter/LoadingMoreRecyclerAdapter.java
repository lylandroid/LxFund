package com.kscf.app.android.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framework.listener.LoadingMoreListener;
import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseDataBindingRecyclerHolder;
import com.kscf.app.android.databinding.ItemRecyclerLoadmoreLayoutBinding;

import java.util.List;

/**
 * Created by luoyl on 2017/3/15.
 */

public class LoadingMoreRecyclerAdapter extends DataBindingRecyclerAdapter {

    //加载更多类型
    private static final int TYPE_FOOT = 88;


    protected LoadingMoreListener mLoadingMoreListener;

    public LoadingMoreRecyclerAdapter(RecyclerView recyclerView, int dataBindingBRVariableId, int itemResId, List datas) {
        super(recyclerView, dataBindingBRVariableId, itemResId, datas);
    }

    public LoadingMoreRecyclerAdapter(RecyclerView recyclerView, int itemResId, List datas) {
        super(recyclerView, itemResId, datas);
    }

    public LoadingMoreRecyclerAdapter(RecyclerView recyclerView, int dataBindingBRVariableId, int itemResId, List datas, boolean isAddItemOnClick) {
        super(recyclerView, dataBindingBRVariableId, itemResId, datas, isAddItemOnClick);
    }


    @Override
    public BaseDataBindingRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOT) {
            return new LoadMoreViewHolder(parent, R.layout.item_recycler_loadmore_layout, false);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseDataBindingRecyclerHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_FOOT:
                holder.onBindData(position, 0, null);
                break;
            default:
                super.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (mItemCount == position) ? TYPE_FOOT : super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    public void setLoadingMoreListener(LoadingMoreListener loadingMoreListener) {
        mLoadingMoreListener = loadingMoreListener;
    }

    public class LoadMoreViewHolder extends BaseDataBindingRecyclerHolder<ItemRecyclerLoadmoreLayoutBinding, Object> {

        private String mLoadingTxt;
        private String mNoMoreDataTxt;

        public LoadMoreViewHolder(ViewGroup parentView, int itemResId, boolean isClick) {
            super(LayoutInflater.from(parentView.getContext()).inflate(itemResId, parentView, false), isClick);
            mLoadingTxt = parentView.getResources().getString(R.string.txt_in_loading);
            mNoMoreDataTxt = parentView.getResources().getString(R.string.txt_no_more_data);
        }

        @Override
        public void onBindData(int position, int dataBindingBRVariableId, Object o) {
            if (mLoadingMoreListener != null) {
                mLoadingMoreListener.onRefreshLoadMore(this);
            }
        }

        public void loading(String txt) {
            mDataBinding.progressBar.setVisibility(View.VISIBLE);
            mDataBinding.tvLoading.setText(TextUtils.isEmpty(txt) ? mLoadingTxt : txt);
        }

        public void loadingFinish(String txt) {
            mDataBinding.progressBar.setVisibility(View.GONE);
            mDataBinding.tvLoading.setText(TextUtils.isEmpty(txt) ? mNoMoreDataTxt : txt);
        }
    }
}
