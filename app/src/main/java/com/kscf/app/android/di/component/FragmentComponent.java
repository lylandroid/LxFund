package com.kscf.app.android.di.component;

import android.app.Activity;

import com.kscf.app.android.di.mode.FragmentModule;
import com.kscf.app.android.di.scope.FragmentScope;
import com.kscf.app.android.ui.fragment.DetailsHomeFundGroupFragment;
import com.kscf.app.android.ui.fragment.DetailsHomeFundSelectedFragment;
import com.kscf.app.android.ui.fragment.FundFragment;
import com.kscf.app.android.ui.fragment.HomeFundGroupFragment;
import com.kscf.app.android.ui.fragment.HomeFundSelectedFragment;
import com.kscf.app.android.ui.fragment.HomeMyAccountFragment;
import com.kscf.app.android.ui.fragment.InviteFriendsFragment;
import com.kscf.app.android.ui.fragment.IWantFixedInvestmentFragment;
import com.kscf.app.android.ui.fragment.LoginFragment;
import com.kscf.app.android.ui.fragment.LxYingFragment;
import com.kscf.app.android.ui.fragment.MessageFragment;
import com.kscf.app.android.ui.fragment.MyBankCardFragment;
import com.kscf.app.android.ui.fragment.QuickAccountFragment01;
import com.kscf.app.android.ui.fragment.QuickAccountFragment02;
import com.kscf.app.android.ui.fragment.QuickAccountFragment03;
import com.kscf.app.android.ui.fragment.RedPackageFragment;
import com.kscf.app.android.ui.fragment.RegisterAgreementFragment;
import com.kscf.app.android.ui.fragment.ResetTransactionPassFragment;
import com.kscf.app.android.ui.fragment.RiskEvaluationFragment;
import com.kscf.app.android.ui.fragment.setting.AccountSettingsFragment;
import com.kscf.app.android.ui.fragment.setting.ContactAddressFragment;
import com.kscf.app.android.ui.fragment.setting.MoreInformationFragment;
import com.kscf.app.android.ui.fragment.setting.MyContactAddressFragment;
import com.kscf.app.android.ui.fragment.setting.MyContactPhoneFragment;
import com.kscf.app.android.ui.fragment.setting.SaveEmailFragment;
import com.kscf.app.android.ui.fragment.setting.SettingsTransactionPassFragment;
import com.kscf.app.android.ui.fragment.setting.UpdateLoginPassFragment;
import com.kscf.app.android.ui.fragment.setting.UpdateTransactionPassFragment;

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

    //基金精选详情Fragment
    void inject(DetailsHomeFundSelectedFragment fragment);

    //基金精选详情Fragment
    void inject(DetailsHomeFundGroupFragment fragment);

    //领先盈Fragment
    void inject(LxYingFragment fragment);

    //红包Fragment
    void inject(RedPackageFragment fragment);

    //更多信息Fragment
    void inject(MoreInformationFragment fragment);

    //我的联系电话Fragment
    void inject(MyContactPhoneFragment fragment);

    //我的联系地址Fragment
    void inject(MyContactAddressFragment fragment);

    //设置交易密码Fragment
    void inject(SettingsTransactionPassFragment fragment);

    //邀请好友Fragment
    void inject(InviteFriendsFragment fragment);

    //注册协议Fragment
    void inject(RegisterAgreementFragment fragment);

    //保存邮箱Fragment
    void inject(SaveEmailFragment fragment);

    //我要定投Fragment
    void inject(IWantFixedInvestmentFragment fragment);


}
