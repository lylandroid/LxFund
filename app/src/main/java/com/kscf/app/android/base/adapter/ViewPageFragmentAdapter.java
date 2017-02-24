package com.kscf.app.android.base.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.kscf.app.android.app.App;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by lyl on 2016/10/3.
 */
public class ViewPageFragmentAdapter extends FragmentStatePagerAdapter {

    private final ViewPager mViewPager;
    public ArrayList<ViewPageInfo> mTabs = new ArrayList<ViewPageInfo>();
    private Map<String, Fragment> mFragments = new ArrayMap<>();

    public ViewPageFragmentAdapter(FragmentManager fm, ViewPager pager) {
        super(fm);
        mViewPager = pager;
        //mViewPager.setAdapter(this);
        mViewPager.setAdapter(this);
    }

    public void addTab(String title, String tag, Class<?> clss, Bundle args) {
        ViewPageInfo viewPageInfo = new ViewPageInfo(title, tag, clss, args);
        addFragment(viewPageInfo);
    }

    public void addAllTab(ArrayList<ViewPageInfo> mTabs) {
        for (ViewPageInfo viewPageInfo : mTabs) {
            addFragment(viewPageInfo);
        }
    }

    private void addFragment(ViewPageInfo info) {
        if (info == null) {
            return;
        }

        mTabs.add(info);
        notifyDataSetChanged();
    }

    /**
     * 移除第一次
     */
    public void remove() {
        remove(0);
    }

    /**
     * 移除一个tab
     *
     * @param index 备注：如果index小于0，则从第一个开始删 如果大于tab的数量值则从最后一个开始删除
     */
    public void remove(int index) {
        if (mTabs.isEmpty()) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        if (index >= mTabs.size()) {
            index = mTabs.size() - 1;
        }

        ViewPageInfo info = mTabs.get(index);
        // 清理缓存
        if (mFragments.containsKey(info.tag))
            mFragments.remove(info.tag);

        mTabs.remove(index);
        notifyDataSetChanged();
    }

    /**
     * 移除所有的tab
     */
    public void removeAll() {
        if (mTabs.isEmpty()) {
            return;
        }
        mFragments.clear();
        mTabs.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        ViewPageInfo info = mTabs.get(position);

        Fragment fragment = mFragments.get(info.tag);
        if (fragment == null) {
            fragment = Fragment.instantiate(App.getInstance(), info.clss.getName(), info.args);
            // 避免重复创建而进行缓存
            mFragments.put(info.tag, fragment);
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position).title;
    }
}