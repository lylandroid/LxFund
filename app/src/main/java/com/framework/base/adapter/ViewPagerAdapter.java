package com.framework.base.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kscf.app.android.app.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyl on 2016/10/3.
 */
public abstract class ViewPagerAdapter<V extends View, Data> extends PagerAdapter {

    protected List<V> mViews;
    protected List<Data> mDatas;
    protected List<String> mTitles;
    protected LayoutInflater mInflater;


    public ViewPagerAdapter(List<V> views, List<Data> datas) {
        this(null, views, datas);
    }

    public ViewPagerAdapter(List<String> titles, List<V> views, List<Data> datas) {
        this.mTitles = titles;
        this.mViews = (views == null ? new ArrayList<V>() : views);
        this.mDatas = (datas == null ? new ArrayList<Data>() : datas);
        mInflater = LayoutInflater.from(App.getInstance());
    }

    /**
     * Get a View that displays the data at the specified position in the data set.
     *
     * @param position The position of the item within the adapter's data set of the item whose view we want.
     * @param pager    The ViewPager that this view will eventually be attached to.
     * @return A View corresponding to the data at the specified position.
     */
    public abstract V getView(int position, ViewPager pager);

    /**
     * Determines whether a page View is associated with a specific key object as
     * returned by instantiateItem(ViewGroup, int).
     *
     * @param view   Page View to check for association with object
     * @param object Object to check for association with view
     * @return true if view is associated with the key object object.
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * Create the page for the given position.
     *
     * @param container The containing View in which the page will be shown.
     * @param position  The page position to be instantiated.
     * @return Returns an Object representing the new page. This does not need
     * to be a View, but can be some other container of the page.
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewPager pager = (ViewPager) container;
        V view;
        if (position < mViews.size()) {
            view = mViews.get(position);
        } else {
            view = getView(position, pager);
            mViews.add(view);
        }
        bindData(position, view, mDatas.get(position));

        pager.addView(view);

        return view;
    }

    public abstract void bindData(int position, V view, Data data);

    /**
     * Remove a page for the given position.
     *
     * @param container The containing View from which the page will be removed.
     * @param position  The page position to be removed.
     * @param view      The same object that was returned by instantiateItem(View, int).
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        ((ViewPager) container).removeView((View) view);
    }

    @Override
    public int getCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public void clear() {
        if (mDatas != null) {
            mDatas.clear();
            mDatas = null;
        }

        if (mViews != null) {
            mViews.clear();
            mViews = null;
        }

        if (mInflater != null) {
            mInflater = null;
        }
    }
}