package com.kscf.app.android.ui.fragment.setting;

import android.view.View;

import com.framework.base.adapter.LoadingMoreRecyclerAdapter;
import com.framework.listener.LoadingMoreListener;
import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentMyContactPhoneBinding;
import com.kscf.app.android.presenter.MyContactPhoneFragmentPresenter;
import com.kscf.app.android.presenter.contract.MyContactPhoneFragmentContract;
import com.kscf.app.android.widget.LoadingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的联系电话Fragment
 * Created by luoyl on 2017/3/6.
 */

public class MyContactPhoneFragment extends BaseFragment<FragmentMyContactPhoneBinding, MyContactPhoneFragmentPresenter> implements MyContactPhoneFragmentContract.View, View.OnClickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_my_contact_phone;
    }


    public static MyContactPhoneFragment newInstance() {
        return new MyContactPhoneFragment();
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
        List<Integer> datas = new ArrayList<>();
        datas.add(1);
        datas.add(1);
        datas.add(1);

        LoadingMoreRecyclerAdapter loadingMoreRecyclerAdapter = new LoadingMoreRecyclerAdapter(
                mDataBinding.recyclerView
                , R.layout.item_recycler_my_contact_phone
                , datas);

        loadingMoreRecyclerAdapter.setLoadingMoreListener(new LoadingMoreListener() {
            @Override
            public void onRefreshLoadMore(LoadingMoreRecyclerAdapter.LoadMoreViewHolder holder) {
                holder.loading(null);
            }
        });

        mDataBinding.recyclerView.setAdapter(loadingMoreRecyclerAdapter);

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
