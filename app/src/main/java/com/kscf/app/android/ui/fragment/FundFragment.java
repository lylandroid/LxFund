package com.kscf.app.android.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.framework.base.adapter.DataBindingViewPagerAdapter;
import com.framework.base.adapter.ViewPagerAdapter;
import com.framework.util.L;
import com.framework.view.RecyclerViewDivider;
import com.kscf.app.android.BR;
import com.kscf.app.android.R;
import com.kscf.app.android.app.App;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.base.adapter.DataBindingRecyclerAdapter;
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
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorButtonSubmitSelect));
        tabLayout.setTabTextColors(getResources().getColor(R.color.text_subtitle_color),
                getResources().getColor(R.color.colorButtonSubmitSelect));
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
        mDataBinding.viewPager.setAdapter(new FundPagerAdapter(viewPagers));
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

        public FundPagerAdapter(List<List<String>> strings) {
            super(null, strings);
        }

        @Override
        public ViewGroup getView(int position, ViewPager pager) {
            return (ViewGroup) mInflater.inflate(R.layout.item_page_viewpager_fund_position, pager, false);
        }

        @Override
        public void dataBindingData(int position, View rootView, List<String> strings) {
            L.i("FundPagerAdapter dataBindingData: " + position);

            mDataBinding.recyclerView.setAdapter(new DataBindingRecyclerAdapter<ItemViewpagerListviewFundPositionBinding, String>(
                    mDataBinding.recyclerView, 0, R.layout.item_viewpager_listview_fund_position, strings
            ));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            switch (position) {
                case 0:
                    title = App.getInstance().getResources().getString(R.string.txt_position);
                    break;
                case 1:
                    title = App.getInstance().getResources().getString(R.string.txt_on_the_way);
                    break;
                default:
                    title = "";
                    break;
            }
            return title;
        }
    }


    @Override
    public void free() {
        super.free();
    }


}