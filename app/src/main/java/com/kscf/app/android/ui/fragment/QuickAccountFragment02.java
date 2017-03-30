package com.kscf.app.android.ui.fragment;

import android.view.View;

import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentQuickAccount2Binding;
import com.kscf.app.android.presenter.QuickAccountFragmentPresenter02;
import com.kscf.app.android.presenter.contract.QuickAccountContract02;
import com.kscf.app.android.widget.LoadingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * 快速开户Fragment
 * Created by luoyl on 2017/2/10.
 */

public class QuickAccountFragment02 extends BaseFragment<FragmentQuickAccount2Binding, QuickAccountFragmentPresenter02> implements QuickAccountContract02.View, View.OnClickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_quick_account_2;
    }


    public static QuickAccountFragment02 newInstance() {
        return new QuickAccountFragment02();
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {
        mLoadingPage.showPage(LoadingPage.STATE_SUCCEED);
        mDataBinding.includeItem.ll1.setSelected(true);
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
