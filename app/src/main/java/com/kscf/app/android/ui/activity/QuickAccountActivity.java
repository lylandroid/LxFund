package com.kscf.app.android.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.kscf.app.android.R;
import com.kscf.app.android.app.App;
import com.kscf.app.android.app.LxConstants;
import com.kscf.app.android.base.BaseActivity;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.ui.fragment.QuickAccountFragment01;
import com.kscf.app.android.ui.fragment.QuickAccountFragment02;
import com.kscf.app.android.ui.fragment.QuickAccountFragment03;
import com.kscf.app.android.widget.LoadingPage;

/**
 * Created by luoyl on 2017/2/9.
 * 快速开户Activity
 */

public class QuickAccountActivity extends BaseActivity {
    @Override
    public int getLayoutResId() {
        return R.layout.activity_quick_account;
    }

    @Override
    public void initInject() {
    }

    @Override
    public void initView() {
        mLoadingPage.showPage(LoadingPage.STATE_SUCCEED);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initEventAndData() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            int fragmentHashCodeValue = extras.getInt(LxConstants.fragmentHashCodeKey, 0);
            BaseFragment showFragment = newFragment(fragmentHashCodeValue);
            showHideFragment(showFragment, null);
        }
    }

    public BaseFragment newFragment(int fragmentId) {
        BaseFragment fragment = null;
        if (fragmentId == QuickAccountFragment01.class.hashCode()) {
            fragment = QuickAccountFragment01.newInstance();
        } else if (fragmentId == QuickAccountFragment02.class.hashCode()) {
            fragment = QuickAccountFragment02.newInstance();
        } else if (fragmentId == QuickAccountFragment03.class.hashCode()) {
            fragment = QuickAccountFragment03.newInstance();
        }
        return fragment;
    }

    public void showHideFragment(Fragment showFragment, Class hideFragmentClazz) {
        showHideFragment(R.id.fl_content, getSupportFragmentManager(), showFragment, hideFragmentClazz);
    }

    /**
     * 添加Fragment到快速开户Activity中
     *
     * @param context
     * @param toFragmentClassHashCode
     * @param isNeedLogin             是否需要登录
     */
    public static void addFragmentToActivity(Context context, int toFragmentClassHashCode, boolean isNeedLogin) {
        Intent intent = new Intent(context, QuickAccountActivity.class);
        intent.putExtra(LxConstants.fragmentHashCodeKey, toFragmentClassHashCode);
        App.getInstance().startTargetActivity(context, intent, isNeedLogin);
    }

    @Override
    protected void onDestroy() {
        App.getInstance().startTargetActivity(this, App.getInstance().getTargetIntent(), false);
        super.onDestroy();
    }
}
