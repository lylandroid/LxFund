package com.kscf.app.android.ui.fragment;

import android.view.View;

import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.base.adapter.DataBindingRecyclerAdapter;
import com.kscf.app.android.databinding.FragmentMessageBinding;
import com.kscf.app.android.databinding.FragmentMyBankCardBinding;
import com.kscf.app.android.databinding.HomeFragmentFundMyAccountBinding;
import com.kscf.app.android.presenter.MessageFragmentPresenter;
import com.kscf.app.android.presenter.MyBankCardFragmentPresenter;
import com.kscf.app.android.presenter.contract.MessageFragmentContract;
import com.kscf.app.android.presenter.contract.MyBankCardFragmentContract;
import com.kscf.app.android.widget.LoadingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息Fragment
 * Created by luoyl on 2017/1/12.
 */

public class MyBankCardFragment extends BaseFragment<FragmentMyBankCardBinding, MyBankCardFragmentPresenter> implements MyBankCardFragmentContract.View, View.OnClickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_my_bank_card;
    }


    public static MyBankCardFragment newInstance() {
        return new MyBankCardFragment();
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
        for (int i = 0; i < 15; i++) {
            datas.add("item " + i);
        }
        mDataBinding.recyclerView.setAdapter(new DataBindingRecyclerAdapter(mDataBinding.recyclerView
                , R.layout.item_recycler_bank_card, datas));

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
