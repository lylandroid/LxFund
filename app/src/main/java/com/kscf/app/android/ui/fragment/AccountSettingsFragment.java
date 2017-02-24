package com.kscf.app.android.ui.fragment;

import android.view.View;

import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentAccountSettingsBinding;
import com.kscf.app.android.presenter.AccountSettingsFragmentPresenter;
import com.kscf.app.android.presenter.contract.AccountSettingsFragmentContract;
import com.kscf.app.android.ui.activity.LoginActivity;
import com.kscf.app.android.widget.LoadingPage;

/**
 * Created by luoyl on 2017/1/24.
 * 账户设置Fragment
 */

public class AccountSettingsFragment extends BaseFragment<FragmentAccountSettingsBinding, AccountSettingsFragmentPresenter> implements AccountSettingsFragmentContract.View, View.OnClickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_account_settings;
    }


    public static AccountSettingsFragment newInstance() {
        return new AccountSettingsFragment();
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
        mDataBinding.itemUpdateLoginPass.setOnClickListener(this);
        mDataBinding.itemUpdateTransactionPass.setOnClickListener(this);
        mDataBinding.itemResetTransactionPass.setOnClickListener(this);
        mDataBinding.itemContactAddress.setOnClickListener(this);


    }

    @Override
    public void initEventAndData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_update_login_pass:
                ((LoginActivity) mActivity).showHideFragment(UpdateLoginPassFragment.newInstance(), null);
                break;
            case R.id.item_update_transaction_pass:
                ((LoginActivity) mActivity).showHideFragment(UpdateTransactionPassFragment.newInstance(), null);
                break;
            case R.id.item_reset_transaction_pass:
                ((LoginActivity) mActivity).showHideFragment(ResetTransactionPassFragment.newInstance(), null);
                break;
            case R.id.item_contact_address:
                ((LoginActivity) mActivity).showHideFragment(ContactAddressFragment.newInstance(), null);
                break;
        }
    }


    @Override
    public void free() {
        super.free();
    }


}
