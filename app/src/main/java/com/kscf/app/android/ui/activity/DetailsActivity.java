package com.kscf.app.android.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseActivity;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.widget.LoadingPage;

/**
 * Created by luoyl on 2017/1/12.
 */

public class DetailsActivity extends BaseActivity {


    @Override
    public int getLayoutResId() {
        return R.layout.activity_details;
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
        toJump();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        //super.onNewIntent(intent);
        setIntent(intent);
        toJump();
    }

    public void toJump() {
        String fragmentClassName = getIntentFragmentClazzName();
        BaseFragment showFragment = newFragment(fragmentClassName);
        if (showFragment != null)
            showHideFragment(showFragment, null);
    }

    public void showHideFragment(Fragment showFragment, Class hideFragmentClazz) {
        showHideFragment(R.id.fl_content, getSupportFragmentManager(), showFragment, hideFragmentClazz);
    }
}
