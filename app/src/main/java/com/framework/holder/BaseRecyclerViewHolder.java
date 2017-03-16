package com.framework.holder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by lyl on 2016/10/3.
 */

public abstract class BaseRecyclerViewHolder<DataBinding extends ViewDataBinding,T> extends RecyclerView.ViewHolder {
    protected int mPosition;
    protected DataBinding mDataBinding;
    protected T mData;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        DataBindingUtil.bind(itemView);
    }

    public BaseRecyclerViewHolder(ViewGroup parentView, int itemResId) {
        this(LayoutInflater.from(parentView.getContext()).inflate(itemResId, parentView, false));
    }

    public final void onBindData(int position, T t) {
        this.mPosition = position;
        this.mData = t;
        onBindRealData();
    }

    protected abstract void onBindRealData();

}