package com.kscf.app.android.ui.fragment.setting;

import android.view.View;

import com.framework.base.adapter.DataBindingRecyclerAdapter;
import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentMyContactAddressBinding;
import com.kscf.app.android.presenter.MyContactAddressFragmentPresenter;
import com.kscf.app.android.presenter.contract.MyContactAddressFragmentContract;
import com.kscf.app.android.ui.activity.DetailsActivity;
import com.kscf.app.android.widget.LoadingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的联系地址Fragment
 * Created by luoyl on 2017/3/9.
 */

public class MyContactAddressFragment extends BaseFragment<FragmentMyContactAddressBinding, MyContactAddressFragmentPresenter> implements MyContactAddressFragmentContract.View, View.OnClickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_my_contact_address;
    }


    public static MyContactAddressFragment newInstance() {
        return new MyContactAddressFragment();
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
        mDataBinding.btnConfirm.setOnClickListener(this);

    }

    @Override
    public void initEventAndData() {
        List<Integer> datas = new ArrayList<>();
        datas.add(1);
        datas.add(1);
        datas.add(1);

        mDataBinding.recyclerView.setAdapter(new DataBindingRecyclerAdapter(
                mDataBinding.recyclerView
                , R.layout.item_recycler_my_contact_address
                , datas));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                mActivity.addFragmentToActivity(mActivity,DetailsActivity.class, ContactAddressFragment.class.getName(), true);
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
