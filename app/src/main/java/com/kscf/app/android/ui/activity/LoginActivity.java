package com.kscf.app.android.ui.activity;

import android.support.v4.app.Fragment;

import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseActivity;
import com.kscf.app.android.ui.fragment.LoginFragment;
import com.kscf.app.android.widget.LoadingPage;

/**
 * Created by luoyl on 2017/1/12.
 */

public class LoginActivity extends BaseActivity {
    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void initInject() {

    }

    @Override
    public void initView() {
        mLoadingPage.showPage(LoadingPage.STATE_SUCCEED);
        //addFragment(R.id.fl_content, getSupportFragmentManager(), LoginFragment.newInstance());
        showHideFragment(LoginFragment.newInstance(), null);


    }

    @Override
    public void initListener() {

    }

    @Override
    public void initEventAndData() {
    }

    public void showHideFragment(Fragment showFragment, Class hideFragmentClazz) {
        showHideFragment(R.id.fl_content, getSupportFragmentManager(), showFragment, hideFragmentClazz);
    }

}
