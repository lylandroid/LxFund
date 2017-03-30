package com.kscf.app.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.kscf.app.android.R;
import com.kscf.app.android.app.App;
import com.kscf.app.android.app.LxConstants;
import com.kscf.app.android.base.BaseActivity;
import com.kscf.app.android.base.BaseFragment;
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
            String fragmentClazzName = extras.getString(LxConstants.fragmentClazzNameKey, null);
            BaseFragment showFragment = newFragment(fragmentClazzName);
            if (showFragment != null)
                showHideFragment(showFragment, null);
        }
    }



    public void showHideFragment(Fragment showFragment, Class hideFragmentClazz) {
        showHideFragment(R.id.fl_content, getSupportFragmentManager(), showFragment, hideFragmentClazz);
    }

    @Override
    protected void onDestroy() {
        App.getInstance().startTargetActivity(this, App.getInstance().getTargetIntent(), false);
        super.onDestroy();
    }
}
