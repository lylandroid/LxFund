package com.kscf.app.android.base.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kscf.app.android.BR;
import com.kscf.app.android.app.App;
import com.kscf.app.android.base.BaseDataBindingRecyclerHolder;
import com.framework.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyl on 2016/10/3.
 */

public class DataBindingRecyclerAdapter<DataBinding extends ViewDataBinding, T> extends RecyclerView.Adapter<BaseDataBindingRecyclerHolder> {
    protected List<T> mDatas;
    protected int mItemCount;
    protected int mItemResId;

    protected LayoutInflater mInflater;

    protected final int mDataBindingBRVariableId;

    /*是否给Item添加点击事件*/
    protected boolean mIsAddItemOnClick;

    /**
     * @param recyclerView
     * @param dataBindingBRVariableId
     * @param itemResId               recyclerView条目的资源id
     * @param datas                   recyclerView展示的数据集合（可以传null）
     */
    public DataBindingRecyclerAdapter(RecyclerView recyclerView, int dataBindingBRVariableId, int itemResId, List datas) {
        this(recyclerView, dataBindingBRVariableId, itemResId, datas, false);
    }

    public DataBindingRecyclerAdapter(RecyclerView recyclerView, int itemResId, List datas) {
        this(recyclerView, BR.itemData, itemResId, datas, false);
    }

    public DataBindingRecyclerAdapter(RecyclerView recyclerView, int dataBindingBRVariableId, int itemResId, List datas, boolean isAddItemOnClick) {
        mDatas = datas != null ? datas : new ArrayList();
        mItemResId = itemResId;
        mIsAddItemOnClick = isAddItemOnClick;
        this.mDataBindingBRVariableId = dataBindingBRVariableId;
        mInflater = LayoutInflater.from(App.getInstance());
        if (recyclerView != null) {
            recyclerView.setAdapter(this);
        }
    }


    @Override
    public BaseDataBindingRecyclerHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        L.i("DataBindingRecyclerAdapter  onCreateViewHolder");
        return new BaseDataBindingRecyclerHolder<DataBinding, T>(mInflater.inflate(mItemResId, parent, false), mIsAddItemOnClick) {
            @Override
            public void onItemClick(int position, View view, DataBinding dataBinding) {
                DataBindingRecyclerAdapter.this.onItemClick(position, view, dataBinding);
            }
        };


    }

    @Override
    public void onBindViewHolder(BaseDataBindingRecyclerHolder holder, int position) {
        L.i("DataBindingRecyclerAdapter  onBindViewHolder");
        holder.onBindData(position, mDataBindingBRVariableId, mDatas.get(position));
    }

    /**
     * @param isLoadMore 数据是否累加
     * @param datas      数据
     */
    public void addDatas(boolean isLoadMore, List datas) {
        if (!isLoadMore) {
            mDatas.clear();
        }
        addDatas(datas);
    }

    /**
     * @param datas 数据
     */
    public void addDatas(List datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void removeData(int index) {
        mDatas.remove(index);
    }

    public void removeData(T data) {
        mDatas.remove(data);
    }


    @Override
    public int getItemCount() {
        mItemCount = mDatas != null ? mDatas.size() : 0;
        return mItemCount;
    }

    public void onItemClick(int position, View v, DataBinding dataBinding) {

    }
}