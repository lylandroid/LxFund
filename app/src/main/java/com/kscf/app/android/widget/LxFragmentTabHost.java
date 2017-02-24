package com.kscf.app.android.widget;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;

public class LxFragmentTabHost extends FragmentTabHost {

    public LxFragmentTabHost(Context context) {
        this(context, null);
    }

    public LxFragmentTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setup(Context context, FragmentManager manager, int containerId) {
        super.setup(context, manager, containerId);
        getTabWidget().setDividerDrawable(null);
    }

    @Override
    public void onTabChanged(String tabId) {
        super.onTabChanged(tabId);
    }
}