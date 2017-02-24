package com.kscf.app.android.ui.fragment;

import android.view.View;

import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentAccountSettingsBinding;
import com.kscf.app.android.databinding.FragmentLoginBinding;
import com.kscf.app.android.databinding.FragmentUpdateLoginPasswordBinding;
import com.kscf.app.android.presenter.AccountSettingsFragmentPresenter;
import com.kscf.app.android.presenter.LoginFragmentPresenter;
import com.kscf.app.android.presenter.UpdateLoginPassFragmentPresenter;
import com.kscf.app.android.presenter.contract.AccountSettingsFragmentContract;
import com.kscf.app.android.presenter.contract.UpdateLoginPassFragmentContract;
import com.kscf.app.android.widget.LoadingPage;

/**
 * Created by luoyl on 2017/1/24.
 * 修改登录密码Fragment
 */

public class UpdateLoginPassFragment extends BaseFragment<FragmentUpdateLoginPasswordBinding, UpdateLoginPassFragmentPresenter> implements UpdateLoginPassFragmentContract.View, View.OnClickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_update_login_password;
    }


    public static UpdateLoginPassFragment newInstance() {
        return new UpdateLoginPassFragment();
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }


    @Override
    public void free() {
        super.free();
    }


}
