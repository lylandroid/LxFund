package com.kscf.app.android.ui.fragment;

import android.support.design.widget.TabLayout;
import android.view.View;

import com.framework.base.adapter.DataBindingRecyclerAdapter;
import com.framework.base.adapter.DataBindingViewPagerAdapter;
import com.kscf.app.android.R;
import com.kscf.app.android.app.App;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentLxYingBinding;
import com.kscf.app.android.databinding.ItemPageViewpagerFundPositionBinding;
import com.kscf.app.android.databinding.ItemViewpagerListviewFundPositionBinding;
import com.kscf.app.android.presenter.LxYingFragmentPresenter;
import com.kscf.app.android.presenter.contract.LxYingFragmentContract;
import com.kscf.app.android.ui.activity.DetailsActivity;
import com.kscf.app.android.widget.LoadingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * 领先盈Fragment
 * Created by luoyl on 2017/3/2.
 */

public class LxYingFragment extends BaseFragment<FragmentLxYingBinding, LxYingFragmentPresenter> implements LxYingFragmentContract.View, View.OnClickListener {


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_lx_ying;
    }


    public static LxYingFragment newInstance() {
        return new LxYingFragment();
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {
        mLoadingPage.showPage(LoadingPage.STATE_SUCCEED);

        TabLayout tabLayout = mDataBinding.tabLayout;
        //tabLayout.addTab(tabLayout.newTab().setText(R.string.txt_position));
        //tabLayout.addTab(tabLayout.newTab().setText(R.string.txt_on_the_way));
        tabLayout.setupWithViewPager(mDataBinding.viewPager);
    }

    @Override
    public void initListener() {
        mDataBinding.btnRecharge.setOnClickListener(this);
        mDataBinding.btnFixedInvestment.setOnClickListener(this);

    }

    @Override
    public void initEventAndData() {

        List<List<String>> viewPagers = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<String> datas = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                datas.add("item " + j);
            }
            viewPagers.add(datas);
        }

        List<Integer> pageResIds = new ArrayList<>();
        pageResIds.add(R.layout.item_page_viewpager_lx_ying_0);
        pageResIds.add(R.layout.item_page_viewpager_lx_ying_0);


        mDataBinding.viewPager.setAdapter(new LxYingPagerAdapter(pageResIds, viewPagers));
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_recharge:

                break;
            case R.id.btn_fixed_investment:
                mActivity.addFragmentToActivity(mActivity,DetailsActivity.class, IWantFixedInvestmentFragment.class.getName(), true);
                break;
        }
    }

    /*private void toAccountSettings() {
        ((LoginActivity) mActivity).showHideFragment(AccountSettingsFragment.newInstance(), null);
    }*/


    /**
     * 泛型List<String>：viewpager中RecyclerView条目数据
     */
    class LxYingPagerAdapter extends DataBindingViewPagerAdapter<ItemPageViewpagerFundPositionBinding, List<String>> {

        public LxYingPagerAdapter(List<Integer> pageResIds, List<List<String>> strings) {
            super(pageResIds, null, strings);
            mTitles.add(App.getInstance().getResources().getString(R.string.txt_position));
            mTitles.add(App.getInstance().getResources().getString(R.string.txt_on_the_way));
        }

        @Override
        public void bindRealData(int position, View rootView, List<String> strings) {

           /* mDataBinding.recyclerView.setAdapter(new DataBindingRecyclerAdapter<ItemViewpagerListviewFundPositionBinding, String>(
                    mDataBinding.recyclerView, 0, R.layout.item_viewpager_listview_fund_position, strings
            ));*/
        }
    }


    @Override
    public void free() {
        super.free();
    }


}
