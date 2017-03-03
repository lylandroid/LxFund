package com.kscf.app.android.ui.fragment;

import android.view.View;

import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.base.adapter.DataBindingRecyclerAdapter;
import com.kscf.app.android.databinding.FragmentMessageBinding;
import com.kscf.app.android.databinding.FragmentRedPackageBinding;
import com.kscf.app.android.presenter.MessageFragmentPresenter;
import com.kscf.app.android.presenter.RedPackageFragmentPresenter;
import com.kscf.app.android.presenter.contract.MessageFragmentContract;
import com.kscf.app.android.presenter.contract.RedPackageFragmentContract;
import com.kscf.app.android.widget.LoadingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息Fragment
 * Created by luoyl on 2017/1/12.
 */

public class RedPackageFragment extends BaseFragment<FragmentRedPackageBinding, RedPackageFragmentPresenter> implements RedPackageFragmentContract.View, View.OnClickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_red_package;
    }


    public static RedPackageFragment newInstance() {
        return new RedPackageFragment();
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
        /*switch (v.getId()) {
            case R.id.btn_login:
                break;
        }*/
    }

    /*private void toAccountSettings() {
        ((LoginActivity) mActivity).showHideFragment(AccountSettingsFragment.newInstance(), null);
    }*/

    @Override
    public void free() {
        super.free();
    }


}
