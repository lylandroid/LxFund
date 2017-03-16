package com.kscf.app.android.ui.fragment;

import android.view.View;

import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentSettingTransactionPassBinding;
import com.kscf.app.android.presenter.SettingsTransactionPassFragmentPresenter;
import com.kscf.app.android.presenter.contract.SettingsTransastionPassFragmentContract;
import com.kscf.app.android.ui.activity.DetailsActivity;
import com.kscf.app.android.widget.LoadingPage;

/**
 * Created by luoyl on 2017/3/10.
 * 设置交易密码Fragment
 */

public class SettingsTransactionPassFragment extends BaseFragment<FragmentSettingTransactionPassBinding, SettingsTransactionPassFragmentPresenter> implements SettingsTransastionPassFragmentContract.View, View.OnClickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_setting_transaction_pass;
    }


    public static SettingsTransactionPassFragment newInstance() {
        return new SettingsTransactionPassFragment();
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
        mDataBinding.itemModifyPass.setOnClickListener(this);
        mDataBinding.itemForgetPass.setOnClickListener(this);


    }

    @Override
    public void initEventAndData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_modify_pass:
                DetailsActivity.addFragmentToDetailsActivity(mActivity,UpdateTransactionPassFragment.class.hashCode(),true);
                break;
            case R.id.item_forget_pass:
                DetailsActivity.addFragmentToDetailsActivity(mActivity,ResetTransactionPassFragment.class.hashCode(),true);
                break;
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
