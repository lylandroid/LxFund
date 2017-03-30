package com.kscf.app.android.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.framework.base.adapter.DataBindingRecyclerAdapter;
import com.framework.base.adapter.DataBindingViewPagerAdapter;
import com.kscf.app.android.BR;
import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentRiskEvaluationBinding;
import com.kscf.app.android.databinding.ItemCheckboxRightTxtGroupBinding;
import com.kscf.app.android.databinding.ItemViewpagerRiskEvaluationBinding;
import com.kscf.app.android.model.bean.RiskEvaluationBean;
import com.kscf.app.android.presenter.RiskEvaluationFragmentPresenter;
import com.kscf.app.android.presenter.contract.RiskEvaluationFragmentContract;
import com.kscf.app.android.widget.LoadingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoyl on 2017/1/22.
 * 风险测评Fragment
 */

public class RiskEvaluationFragment extends BaseFragment<FragmentRiskEvaluationBinding, RiskEvaluationFragmentPresenter> implements RiskEvaluationFragmentContract.View, View.OnClickListener {


    private RiskEvaluationAdapter mRiskEvaluationAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_risk_evaluation;
    }


    public static RiskEvaluationFragment newInstance() {
        return new RiskEvaluationFragment();
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
        mDataBinding.includePage.btnConfirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm://点击重新测评，跳转到第一页
                mDataBinding.includePage.rootLayout.setVisibility(View.GONE);
                mRiskEvaluationAdapter.setAllCheckBoxState(false);
                mDataBinding.viewPager.setCurrentItem(0, false);
                break;
            case R.id.tv_up_topic://点击上一题，跳转到上一页
                int currentItemIndex = mDataBinding.viewPager.getCurrentItem();
                mDataBinding.viewPager.setCurrentItem(Math.max(0, currentItemIndex - 1));
                break;
        }
    }

    @Override
    public void initEventAndData() {
        RiskEvaluationBean riskEvaluationBean = new RiskEvaluationBean();
        riskEvaluationBean.pageItems = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            RiskEvaluationBean.PageItem item = riskEvaluationBean.new PageItem();
            item.recyclerItems = new ArrayList<>();
            item.title = "1.您目前所处的年龄阶段？";
            for (int j = 0; j < 3; j++) {
                RiskEvaluationBean.PageItem.RecyclerItem recyclerItem = item.new RecyclerItem();
                recyclerItem.des = "60岁以上";

                item.recyclerItems.add(recyclerItem);
            }
            riskEvaluationBean.pageItems.add(item);
        }

        List<Integer> pageResIds = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            pageResIds.add(R.layout.item_viewpager_risk_evaluation);
        }

        mRiskEvaluationAdapter = new RiskEvaluationAdapter(pageResIds, riskEvaluationBean.pageItems);
        mDataBinding.viewPager.setAdapter(mRiskEvaluationAdapter);

    }

    class RiskEvaluationAdapter extends DataBindingViewPagerAdapter<ItemViewpagerRiskEvaluationBinding, RiskEvaluationBean.PageItem> {

        public RiskEvaluationAdapter(List<Integer> pageResIds, List<RiskEvaluationBean.PageItem> beans) {
            super(pageResIds, null, beans);
        }

        @Override
        public void bindRealData(int position, View view, RiskEvaluationBean.PageItem bean) {
            mDataBinding.tvTitle.setText(bean.title);
            mDataBinding.recyclerView.setAdapter(new RecyclerDataBindingRecyclerAdapter(mDataBinding.recyclerView
                    , BR.itemData
                    , R.layout.item_checkbox_right_txt_group
                    , bean.recyclerItems, true));
            //设置当前页码
            mDataBinding.includeItem.tvIndexCount.setText(String.format(getResources().getString(R.string.txtCurrentPageAndTotalPage), position + 1, getCount()));
            //给上一页按钮点击事件
            mDataBinding.includeItem.tvUpTopic.setOnClickListener(RiskEvaluationFragment.this);

        }

        /**
         * 设置所有checkBox的状态是否选中
         *
         * @param allCheckBoxState
         */
        public void setAllCheckBoxState(boolean allCheckBoxState) {
            if (getCount() > 0) {
                for (RiskEvaluationBean.PageItem item : mPageDatas) {
                    int len = item.recyclerItems != null ? item.recyclerItems.size() : 0;
                    if (len > 0) {
                        for (RiskEvaluationBean.PageItem.RecyclerItem recyclerItem : item.recyclerItems) {
                            //recyclerItem.setIsCheck(allCheckBoxState);
                            recyclerItem.isCheck = allCheckBoxState;
                        }
                    }
                }
            }

        }


        /**
         * 风险测评页面RecyclerView对应的Adapter
         */
        class RecyclerDataBindingRecyclerAdapter extends DataBindingRecyclerAdapter<ItemCheckboxRightTxtGroupBinding, RiskEvaluationBean.PageItem.RecyclerItem> {
            public RecyclerDataBindingRecyclerAdapter(RecyclerView recyclerView, int dataBindingBRVariableId, int itemResId, List datas, boolean isAddItemOnClick) {
                super(recyclerView, dataBindingBRVariableId, itemResId, datas, isAddItemOnClick);
            }

            @Override
            public void onItemClick(int position, View v, ItemCheckboxRightTxtGroupBinding dataBinding) {
                int len = mDatas.size();
                for (int i = 0; i < len; i++) {
                    mDatas.get(i).setIsCheck((i == position));
                }
                int currentItemIndex = RiskEvaluationFragment.this.mDataBinding.viewPager.getCurrentItem();
                int pageCount = mRiskEvaluationAdapter.getCount();
                RiskEvaluationFragment.this.mDataBinding.viewPager.setCurrentItem(Math.min(pageCount - 1, currentItemIndex + 1));
                if (currentItemIndex + 1 == pageCount) {
                    toFinishPage();
                }
            }
        }
    }

    /**
     * 风险测评完成后，跳转到结果页面
     */
    public void toFinishPage() {
        mDataBinding.includePage.rootLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void free() {
        if (mRiskEvaluationAdapter != null) {
            mRiskEvaluationAdapter.clear();
            mRiskEvaluationAdapter = null;
        }
        super.free();
    }


}