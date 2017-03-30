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
public abstract class ViewPagerAdapter<Data> extends PagerAdapter {

    protected List<Integer> mPageResIds;
    protected List<Data> mPageDatas;
    protected List<String> mTitles;
    protected LayoutInflater mInflater;


    public ViewPagerAdapter(List<Integer> views, List<Data> pageDatas) {
        this(views, null, pageDatas);
    }

    public ViewPagerAdapter(List<Integer> views, List<String> titles, List<Data> pageDatas) {
        this.mTitles = titles == null ? new ArrayList<String>() : titles;
        this.mPageResIds = (views == null ? new ArrayList<Integer>() : views);
        this.mPageDatas = (pageDatas == null ? new ArrayList<Data>() : pageDatas);
        mInflater = LayoutInflater.from(App.getInstance());
    }

    /**
     * Get a View that displays the data at the specified position in the data set.
     *
     * @param position The position of the item within the adapter's data set of the item whose view we want.
     * @param pager    The ViewPager that this view will eventually be attached to.
     * @return A View corresponding to the data at the specified position.
     */
    public ViewGroup getView(int position, int pageResId, ViewPager pager) {
        return (ViewGroup) mInflater.inflate(pageResId, pager, false);
    }

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
        Integer resViewId = 0;
        View view;
        if (position < mPageResIds.size()) {
            resViewId = mPageResIds.get(position);
        }
        view = getView(position, resViewId, (ViewPager) container);
        bindData(position, view, mPageDatas.get(position));
        container.addView(view);
        return view;
    }

    public abstract void bindData(int position, View view, Data data);

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
    public CharSequence getPageTitle(int position) {
        return (mTitles != null && mTitles.size() > 0)
                ? mTitles.get(position)
                : super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return mPageResIds != null ? mPageResIds.size() : 0;
    }

    public void clear() {
        if (mPageDatas != null) {
            mPageDatas.clear();
            mPageDatas = null;
        }

        if (mPageResIds != null) {
            mPageResIds.clear();
            mPageResIds = null;
        }

        if (mInflater != null) {
            mInflater = null;
        }
    }
}