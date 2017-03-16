package com.kscf.app.android.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.kscf.app.android.R;
import com.kscf.app.android.app.App;
import com.kscf.app.android.app.LxConstants;
import com.kscf.app.android.base.BaseActivity;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.ui.fragment.AccountSettingsFragment;
import com.kscf.app.android.ui.fragment.ContactAddressFragment;
import com.kscf.app.android.ui.fragment.DetailsHomeFundGroupFragment;
import com.kscf.app.android.ui.fragment.DetailsHomeFundSelectedFragment;
import com.kscf.app.android.ui.fragment.FundFragment;
import com.kscf.app.android.ui.fragment.LxYingFragment;
import com.kscf.app.android.ui.fragment.MessageFragment;
import com.kscf.app.android.ui.fragment.MoreInformationFragment;
import com.kscf.app.android.ui.fragment.MyBankCardFragment;
import com.kscf.app.android.ui.fragment.MyContactAddressFragment;
import com.kscf.app.android.ui.fragment.MyContactPhoneFragment;
import com.kscf.app.android.ui.fragment.RedPackageFragment;
import com.kscf.app.android.ui.fragment.RegisterAgreementFragment;
import com.kscf.app.android.ui.fragment.ResetTransactionPassFragment;
import com.kscf.app.android.ui.fragment.RiskEvaluationFragment;
import com.kscf.app.android.ui.fragment.SettingsTransactionPassFragment;
import com.kscf.app.android.ui.fragment.UpdateLoginPassFragment;
import com.kscf.app.android.ui.fragment.UpdateTransactionPassFragment;
import com.kscf.app.android.widget.LoadingPage;

/**
 * Created by luoyl on 2017/1/12.
 */

public class DetailsActivity extends BaseActivity {


    @Override
    public int getLayoutResId() {
        return R.layout.activity_details;
    }

    @Override
    public void initInject() {

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
        toJump();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        toJump();
    }

    public void toJump() {
        int fragmentHashCodeValue = getIntentFragmentHashCodeValue();
        BaseFragment showFragment = newFragment(fragmentHashCodeValue);
        showHideFragment(showFragment, null);
    }

    public int getIntentFragmentHashCodeValue() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int fragmentHashCodeValue = 0;
        if (extras != null) {
            fragmentHashCodeValue = extras.getInt(LxConstants.fragmentHashCodeKey, 0);
        }
        return fragmentHashCodeValue;
    }

    public BaseFragment newFragment(int fragmentId) {
        BaseFragment fragment = null;
        if (fragmentId == MessageFragment.class.hashCode()) {
            fragment = MessageFragment.newInstance();
        } else if (fragmentId == MyBankCardFragment.class.hashCode()) {
            fragment = MyBankCardFragment.newInstance();
        } else if (fragmentId == FundFragment.class.hashCode()) {
            fragment = FundFragment.newInstance();
        } else if (fragmentId == DetailsHomeFundSelectedFragment.class.hashCode()) {
            fragment = DetailsHomeFundSelectedFragment.newInstance();
        } else if (fragmentId == DetailsHomeFundGroupFragment.class.hashCode()) {
            fragment = DetailsHomeFundGroupFragment.newInstance();
        } else if (fragmentId == RegisterAgreementFragment.class.hashCode()) {
            fragment = RegisterAgreementFragment.newInstance();
        } else if (fragmentId == LxYingFragment.class.hashCode()) {
            fragment = LxYingFragment.newInstance();
        } else if (fragmentId == RedPackageFragment.class.hashCode()) {
            fragment = RedPackageFragment.newInstance();
        } else if (fragmentId == AccountSettingsFragment.class.hashCode()) {
            fragment = AccountSettingsFragment.newInstance();
        } else if (fragmentId == MoreInformationFragment.class.hashCode()) {
            fragment = MoreInformationFragment.newInstance();
        } else if (fragmentId == MyContactPhoneFragment.class.hashCode()) {
            fragment = MyContactPhoneFragment.newInstance();
        } else if (fragmentId == MyContactAddressFragment.class.hashCode()) {
            fragment = MyContactAddressFragment.newInstance();
        } else if (fragmentId == ContactAddressFragment.class.hashCode()) {
            fragment = ContactAddressFragment.newInstance();
        } else if (fragmentId == SettingsTransactionPassFragment.class.hashCode()) {
            fragment = SettingsTransactionPassFragment.newInstance();
        } else if (fragmentId == UpdateLoginPassFragment.class.hashCode()) {
            fragment = UpdateLoginPassFragment.newInstance();
        }else if (fragmentId == UpdateTransactionPassFragment.class.hashCode()) {
            fragment = UpdateTransactionPassFragment.newInstance();
        } else if (fragmentId == ResetTransactionPassFragment.class.hashCode()) {
            fragment = ResetTransactionPassFragment.newInstance();
        }else if (fragmentId == RiskEvaluationFragment.class.hashCode()) {
            fragment = RiskEvaluationFragment.newInstance();
        }
        return fragment;
    }

    public void showHideFragment(Fragment showFragment, Class hideFragmentClazz) {
        showHideFragment(R.id.fl_content, getSupportFragmentManager(), showFragment, hideFragmentClazz);
    }

    /**
     * 添加Fragment到详情Activity中
     *
     * @param context
     * @param toFragmentClassHashCode
     * @param isNeedLogin             是否需要登录
     */
    public static void addFragmentToDetailsActivity(Context context, int toFragmentClassHashCode, boolean isNeedLogin) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(LxConstants.fragmentHashCodeKey, toFragmentClassHashCode);
        App.getInstance().startTargetActivity(context, intent, isNeedLogin);
    }
}
