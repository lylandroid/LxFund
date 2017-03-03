package com.kscf.app.android.ui.fragment;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.base.adapter.DataBindingRecyclerAdapter;
import com.kscf.app.android.databinding.HomeFragmentFundGroupBinding;
import com.kscf.app.android.model.bean.HomeFundSelectedBannerBean;
import com.kscf.app.android.model.bean.HomeFundSelectedRecyclerBean;
import com.kscf.app.android.model.bean.rxbus.LoginSuccessBusBean;
import com.kscf.app.android.presenter.HomeFundGroupPresenter;
import com.kscf.app.android.presenter.contract.HomeFundGroupContract;
import com.kscf.app.android.ui.activity.DetailsActivity;
import com.kscf.app.android.ui.activity.MainActivity;
import com.kscf.app.android.ui.holder.BannerImageHolderView;
import com.kscf.app.android.util.LxSPUtils;
import com.kscf.app.android.widget.LoadingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoyl on 2017/1/3.
 * 主页基金组合Fragment
 */

public class HomeFundGroupFragment extends BaseFragment<HomeFragmentFundGroupBinding, HomeFundGroupPresenter> implements HomeFundGroupContract.View {
    @Override
    public int getLayoutResId() {
        return R.layout.home_fragment_fund_group;
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
        mPresenter.start();

        List<HomeFundSelectedRecyclerBean> lists = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            HomeFundSelectedRecyclerBean bean = new HomeFundSelectedRecyclerBean();
            lists.add(bean);
        }


        new DataBindingRecyclerAdapter(mDataBinding.linearRecyclerView
                , BR.itemData
                , R.layout.item_recycler_home_fund_selected
                , lists, true) {
            @Override
            public void onItemClick(int position, View v, ViewDataBinding dataBinding) {
                DetailsActivity.addFragmentToDetailsActivity(mActivity, DetailsHomeFundGroupFragment.class.hashCode(), true);
            }
        };
        /*mDataBinding.includeTop.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LxSPUtils.clearAll(mActivity);
            }
        });*/

    }

    @Override
    public void onResume() {
        super.onResume();
       /* mDataBinding.includeTop.convenientBanner.startTurning(3000);*/
    }

    @Override
    public void onPause() {
        super.onPause();
        /*mDataBinding.includeTop.convenientBanner.stopTurning();*/
    }


    @Override
    public void showBanner(List<HomeFundSelectedBannerBean> bannerBeans) {
        /*mDataBinding.includeTop.convenientBanner.setPages(
                new CBViewHolderCreator<BannerImageHolderView>() {
                    @Override
                    public BannerImageHolderView createHolder() {
                        return new BannerImageHolderView();
                    }
                }, bannerBeans)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);*/
    }
}
