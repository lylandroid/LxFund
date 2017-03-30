package com.kscf.app.android.ui.fragment;

import android.support.design.widget.TabLayout;
import android.view.View;

import com.framework.base.adapter.DataBindingRecyclerAdapter;
import com.framework.base.adapter.DataBindingViewPagerAdapter;
import com.kscf.app.android.R;
import com.kscf.app.android.app.App;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentFundBinding;
import com.kscf.app.android.databinding.ItemPageViewpagerFundPositionBinding;
import com.kscf.app.android.databinding.ItemViewpagerListviewFundPositionBinding;
import com.kscf.app.android.presenter.FundFragmentPresenter;
import com.kscf.app.android.presenter.contract.FundFragmentContract;
import com.kscf.app.android.widget.LoadingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * 基金Fragment
 * Created by luoyl on 2017/1/12.
 */

public class FundFragment extends BaseFragment<FragmentFundBinding, FundFragmentPresenter> implements FundFragmentContract.View, View.OnClickListener {


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_fund;
    }


    public static FundFragment newInstance() {
        return new FundFragment();
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

        List<Integer> pageResIds = new ArrayList<>(2);
        pageResIds.add(R.layout.item_page_viewpager_fund_position);
        pageResIds.add(R.layout.item_page_viewpager_fund_position);


        mDataBinding.viewPager.setAdapter(new FundPagerAdapter(pageResIds, viewPagers));
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);


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


    /**
     * 泛型List<String>：viewpager中RecyclerView条目数据
     */
    class FundPagerAdapter extends DataBindingViewPagerAdapter<ItemPageViewpagerFundPositionBinding, List<String>> {

        public FundPagerAdapter(List<Integer> pageResIds, List<List<String>> strings) {
            super(pageResIds, null, strings);
            mTitles.add(App.getInstance().getResources().getString(R.string.txt_position));
            mTitles.add(App.getInstance().getResources().getString(R.string.txt_on_the_way));

        }

        @Override
        public void bindRealData(int position, View rootView, List<String> strings) {
            mDataBinding.recyclerView.setAdapter(new DataBindingRecyclerAdapter<ItemViewpagerListviewFundPositionBinding, String>(
                    mDataBinding.recyclerView, 0, R.layout.item_viewpager_listview_fund_position, strings
            ));
        }
    }


    @Override
    public void free() {
        super.free();
    }


}
