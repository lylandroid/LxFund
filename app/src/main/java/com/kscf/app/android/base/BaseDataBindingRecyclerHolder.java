package com.kscf.app.android.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by lyl on 2016/10/3.
 */

public abstract class BaseDataBindingRecyclerHolder<DataBinding extends ViewDataBinding, T> extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected DataBinding mDataBinding;

    public BaseDataBindingRecyclerHolder(View itemView, boolean isItemOnClick) {
        super(itemView);
        mDataBinding = DataBindingUtil.bind(itemView);
        if (isItemOnClick) {
            itemView.setOnClickListener(this);
        }
    }

    public BaseDataBindingRecyclerHolder(ViewGroup parentView, int itemResId, boolean isItemOnClick) {
        this(LayoutInflater.from(parentView.getContext()).inflate(itemResId, parentView, false), isItemOnClick);
    }

    public void onBindData(int position, int dataBindingBRVariableId, T t) {
        mDataBinding.setVariable(dataBindingBRVariableId, t);
        mDataBinding.executePendingBindings();
    }

    public void onItemClick(int position, View view,DataBinding dataBinding) {

    }

    @Override
    public void onClick(View v) {
        onItemClick(getLayoutPosition(), v,getDataBinding());
    }

    public DataBinding getDataBinding() {
        return mDataBinding;
    }

}