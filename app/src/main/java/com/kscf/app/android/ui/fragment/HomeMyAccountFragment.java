package com.kscf.app.android.ui.fragment;

import android.content.Intent;
import android.view.View;

import com.kscf.app.android.R;
import com.kscf.app.android.app.App;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.HomeFragmentFundMyAccountBinding;
import com.kscf.app.android.presenter.HomeFundMyAccountPresenter;
import com.kscf.app.android.presenter.contract.HomeFundMyAccountContract;
import com.kscf.app.android.ui.activity.DetailsActivity;
import com.kscf.app.android.ui.activity.LoginActivity;
import com.kscf.app.android.ui.activity.MainActivity;
import com.kscf.app.android.ui.dialog.PayPasswordFragmentDialog;
import com.kscf.app.android.widget.LoadingPage;

/**
 * Created by luoyl on 2017/1/3.
 * 主页,我的账户Fragment
 */

public class HomeMyAccountFragment extends BaseFragment<HomeFragmentFundMyAccountBinding, HomeFundMyAccountPresenter> implements HomeFundMyAccountContract.View, View.OnClickListener {

    private PayPasswordFragmentDialog mPayPasswordDialog;

    @Override
    public int getLayoutResId() {
        return R.layout.home_fragment_fund_my_account;
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {
        mLoadingPage.showPage(LoadingPage.STATE_SUCCEED);

        mPayPasswordDialog = new PayPasswordFragmentDialog();


    }

    @Override
    public void initListener() {
        mDataBinding.includeToolbar.ivIconHead.setOnClickListener(this);

        mDataBinding.itemNews.setOnClickListener(this);
        mDataBinding.itemBankCard.setOnClickListener(this);
        mDataBinding.itemLxYing.setOnClickListener(this);
        mDataBinding.itemFund.setOnClickListener(this);
        mDataBinding.itemWelfare.setOnClickListener(this);

        mDataBinding.btnConfirm.setOnClickListener(this);

        mDataBinding.itemRiskEvaluation.setOnClickListener(this);

    }

    @Override
    public void initEventAndData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_icon_head:
                //toLoginActivity();
                toSettingPage();
                break;
            case R.id.btn_confirm:
                mPayPasswordDialog.show(getFragmentManager());
                break;
            case R.id.item_news:
                DetailsActivity.addFragmentToDetailsActivity(mActivity, MessageFragment.class.hashCode(), true);
                break;
            case R.id.item_bank_card:
                DetailsActivity.addFragmentToDetailsActivity(mActivity, MyBankCardFragment.class.hashCode(), true);
                break;
            case R.id.item_fund:
                DetailsActivity.addFragmentToDetailsActivity(mActivity, FundFragment.class.hashCode(), true);
                break;
            case R.id.item_lx_ying:
                DetailsActivity.addFragmentToDetailsActivity(mActivity, LxYingFragment.class.hashCode(), true);
                break;
            case R.id.item_welfare:
                DetailsActivity.addFragmentToDetailsActivity(mActivity, RedPackageFragment.class.hashCode(), true);
                break;
            case R.id.item_risk_evaluation:
                DetailsActivity.addFragmentToDetailsActivity(mActivity, RiskEvaluationFragment.class.hashCode(), true);
                break;

        }
    }

    private void toSettingPage() {
        DetailsActivity.addFragmentToDetailsActivity(mActivity, AccountSettingsFragment.class.hashCode(), true);
    }

    public void toLoginActivity() {
        Intent intent = new Intent(mActivity, LoginActivity.class);
        App.getInstance().startTargetActivity(mActivity, intent, false);
    }
}
