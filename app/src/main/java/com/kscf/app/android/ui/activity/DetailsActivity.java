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
import com.kscf.app.android.ui.fragment.DetailsHomeFundGroupFragment;
import com.kscf.app.android.ui.fragment.DetailsHomeFundSelectedFragment;
import com.kscf.app.android.ui.fragment.FundFragment;
import com.kscf.app.android.ui.fragment.LxYingFragment;
import com.kscf.app.android.ui.fragment.MessageFragment;
import com.kscf.app.android.ui.fragment.MyBankCardFragment;
import com.kscf.app.android.ui.fragment.RedPackageFragment;
import com.kscf.app.android.ui.fragment.RegisterAgreementFragment;
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
