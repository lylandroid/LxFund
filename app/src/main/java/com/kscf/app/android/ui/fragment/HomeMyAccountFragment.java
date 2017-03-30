package com.kscf.app.android.ui.fragment;

import android.content.Intent;
import android.util.SparseArray;
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
import com.kscf.app.android.ui.fragment.setting.AccountSettingsFragment;
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
        //邀请好友item_invite_friends
        mDataBinding.itemInviteFriends.setOnClickListener(this);

    }

    @Override
    public void initEventAndData() {

    }

    SparseArray<String> mFragmentClassNameArray;

    public String getFragmentClassName(int resId) {
        if (mFragmentClassNameArray == null) {
            mFragmentClassNameArray = new SparseArray<>(8);
            mFragmentClassNameArray.put(R.id.iv_icon_head, AccountSettingsFragment.class.getName());
            mFragmentClassNameArray.put(R.id.item_news, MessageFragment.class.getName());
            mFragmentClassNameArray.put(R.id.item_bank_card, MyBankCardFragment.class.getName());
            mFragmentClassNameArray.put(R.id.item_fund, FundFragment.class.getName());
            mFragmentClassNameArray.put(R.id.item_lx_ying, LxYingFragment.class.getName());
            mFragmentClassNameArray.put(R.id.item_welfare, RedPackageFragment.class.getName());
            mFragmentClassNameArray.put(R.id.item_risk_evaluation, RiskEvaluationFragment.class.getName());
            mFragmentClassNameArray.put(R.id.item_invite_friends, InviteFriendsFragment.class.getName());
        }
        return mFragmentClassNameArray.get(resId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                mPayPasswordDialog.show(getFragmentManager());
                break;
            default:
                mActivity.addFragmentToActivity(mActivity, DetailsActivity.class, getFragmentClassName(v.getId()), true);
                break;

        }
    }


    @Override
    public void free() {
        if(mFragmentClassNameArray != null){
            mFragmentClassNameArray.clear();
            mFragmentClassNameArray = null;

        }
        super.free();
    }
}
