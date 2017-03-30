package com.kscf.app.android.ui.fragment;

import android.view.View;

import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentIWantFixedInvestmentBinding;
import com.kscf.app.android.presenter.IWantFixedInvestmentFragmentPresenter;
import com.kscf.app.android.presenter.contract.MessageFragmentContract;
import com.kscf.app.android.widget.LoadingPage;

/**
 * 我要定投Fragment
 * Created by luoyl on 2017/3/27.
 */

public class IWantFixedInvestmentFragment extends BaseFragment<FragmentIWantFixedInvestmentBinding, IWantFixedInvestmentFragmentPresenter> implements MessageFragmentContract.View, View.OnClickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_i_want_fixed_investment;
    }


    public static IWantFixedInvestmentFragment newInstance() {
        return new IWantFixedInvestmentFragment();
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
        mDataBinding.ivCheckProtocol.setOnClickListener(this);

    }

    @Override
    public void initEventAndData() {

     /*   List<String> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            datas.add("item " + i);
        }
        mDataBinding.recyclerView.setAdapter(new DataBindingRecyclerAdapter(mDataBinding.recyclerView
                , R.layout.item_recycler_message
                , datas));*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_check_protocol:
                boolean checkBoxIsSelected = mDataBinding.ivCheckProtocol.isSelected();
                mDataBinding.ivCheckProtocol.setSelected(!checkBoxIsSelected);
                break;
        }
    }

    /*private void toAccountSettings() {
        ((LoginActivity) mActivity).showHideFragment(AccountSettingsFragment.newInstance(), null);
    }*/

    @Override
    public void free() {
        super.free();
    }


}
