package com.kscf.app.android.di.component;

import android.app.Activity;

import com.kscf.app.android.di.mode.FragmentModule;
import com.kscf.app.android.di.scope.FragmentScope;
import com.kscf.app.android.ui.fragment.AccountSettingsFragment;
import com.kscf.app.android.ui.fragment.ContactAddressFragment;
import com.kscf.app.android.ui.fragment.DetailsHomeFundSelectedFragment;
import com.kscf.app.android.ui.fragment.FundFragment;
import com.kscf.app.android.ui.fragment.HomeFundGroupFragment;
import com.kscf.app.android.ui.fragment.HomeFundSelectedFragment;
import com.kscf.app.android.ui.fragment.HomeMyAccountFragment;
import com.kscf.app.android.ui.fragment.LoginFragment;
import com.kscf.app.android.ui.fragment.MessageFragment;
import com.kscf.app.android.ui.fragment.MyBankCardFragment;
import com.kscf.app.android.ui.fragment.QuickAccountFragment01;
import com.kscf.app.android.ui.fragment.QuickAccountFragment02;
import com.kscf.app.android.ui.fragment.QuickAccountFragment03;
import com.kscf.app.android.ui.fragment.ResetTransactionPassFragment;
import com.kscf.app.android.ui.fragment.RiskEvaluationFragment;
import com.kscf.app.android.ui.fragment.UpdateLoginPassFragment;
import com.kscf.app.android.ui.fragment.UpdateTransactionPassFragment;

import dagger.Component;

/**
 * Created by luoyl on 2016/12/11.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(HomeFundSelectedFragment fragment);

    void inject(HomeFundGroupFragment fragment);


    void inject(HomeMyAccountFragment fragment);

    void inject(LoginFragment fragment);

    void inject(RiskEvaluationFragment fragment);

    void inject(AccountSettingsFragment fragment);

    void inject(UpdateLoginPassFragment fragment);

    void inject(UpdateTransactionPassFragment fragment);

    void inject(ResetTransactionPassFragment fragment);

    void inject(ContactAddressFragment fragment);

    void inject(FundFragment fragment);

    void inject(MessageFragment fragment);

    void inject(MyBankCardFragment fragment);

    void inject(QuickAccountFragment01 fragment);

    void inject(QuickAccountFragment02 fragment);

    void inject(QuickAccountFragment03 fragment);
    //基金详情Fragment
    void inject(DetailsHomeFundSelectedFragment fragment);




}
