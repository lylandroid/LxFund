package com.kscf.app.android.ui.fragment;

import android.view.View;

import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.base.adapter.DataBindingRecyclerAdapter;
import com.kscf.app.android.databinding.FragmentMessageBinding;
import com.kscf.app.android.presenter.MessageFragmentPresenter;
import com.kscf.app.android.presenter.contract.MessageFragmentContract;
import com.kscf.app.android.widget.LoadingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息Fragment
 * Created by luoyl on 2017/1/12.
 */

public class MessageFragment extends BaseFragment<FragmentMessageBinding, MessageFragmentPresenter> implements MessageFragmentContract.View, View.OnClickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_message;
    }


    public static MessageFragment newInstance() {
        return new MessageFragment();
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
        mDataBinding.recyclerView.setAdapter(new DataBindingRecyclerAdapter(mDataBinding.recyclerView
                , R.layout.item_recycler_message
                , datas));
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
