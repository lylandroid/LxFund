package com.kscf.app.android.ui.fragment;

import android.view.View;

import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentQuickAccount3Binding;
import com.kscf.app.android.presenter.QuickAccountFragmentPresenter03;
import com.kscf.app.android.presenter.contract.QuickAccountContract03;
import com.kscf.app.android.widget.LoadingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * 快速开户Fragment
 * Created by luoyl on 2017/2/10.
 */

public class QuickAccountFragment03 extends BaseFragment<FragmentQuickAccount3Binding, QuickAccountFragmentPresenter03> implements QuickAccountContract03.View, View.OnClickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_quick_account_3;
    }


    public static QuickAccountFragment03 newInstance() {
        return new QuickAccountFragment03();
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

        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            datas.add("item " + i);
        }
        //mDataBinding.recyclerView.setAdapter(new DataBindingRecyclerAdapter(mDataBinding.recyclerView, R.layout.item_recycler_message, datas));
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
