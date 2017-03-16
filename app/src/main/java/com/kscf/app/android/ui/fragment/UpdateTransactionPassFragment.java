package com.kscf.app.android.ui.fragment;

import android.view.View;

import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentAccountSettingsBinding;
import com.kscf.app.android.databinding.FragmentUpdateTransactionPassBinding;
import com.kscf.app.android.presenter.AccountSettingsFragmentPresenter;
import com.kscf.app.android.presenter.UpdateTransactionPassFragmentPresenter;
import com.kscf.app.android.presenter.contract.AccountSettingsFragmentContract;
import com.kscf.app.android.presenter.contract.UpdateTransactionPassFragmentContract;
import com.kscf.app.android.widget.LoadingPage;

/**
 * Created by luoyl on 2017/1/24.
 * 修改交易密码Fragment
 */

public class UpdateTransactionPassFragment extends BaseFragment<FragmentUpdateTransactionPassBinding, UpdateTransactionPassFragmentPresenter> implements UpdateTransactionPassFragmentContract.View, View.OnClickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_update_transaction_pass;
    }


    public static UpdateTransactionPassFragment newInstance() {
        return new UpdateTransactionPassFragment();
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


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
