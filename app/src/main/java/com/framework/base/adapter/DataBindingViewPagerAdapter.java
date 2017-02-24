package com.framework.base.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

import java.util.List;

/**
 * Created by lyl on 2016/10/3.
 */
public abstract class DataBindingViewPagerAdapter<DataBinding extends ViewDataBinding, Data> extends ViewPagerAdapter<View, Data> {

    protected DataBinding mDataBinding;

    public DataBindingViewPagerAdapter(List<View> views, List<Data> datas) {
        super(views, datas);
    }

    @Override
    public void bindData(int position, View rootView, Data data) {
        mDataBinding = DataBindingUtil.bind(rootView);
        dataBindingData(position, rootView, data);
    }

    public abstract void dataBindingData(int position, View rootView, Data data);


    @Override
    public void clear() {
        super.clear();
        if (mDataBinding != null) {
            mDataBinding.unbind();
            mDataBinding = null;
        }
    }
}