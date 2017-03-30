package com.kscf.app.android.ui.fragment.setting;

import android.util.SparseArray;
import android.view.View;

import com.kscf.app.android.R;
import com.kscf.app.android.base.BaseFragment;
import com.kscf.app.android.databinding.FragmentAccountSettingsBinding;
import com.kscf.app.android.presenter.AccountSettingsFragmentPresenter;
import com.kscf.app.android.presenter.contract.AccountSettingsFragmentContract;
import com.kscf.app.android.ui.activity.DetailsActivity;
import com.kscf.app.android.ui.activity.MainActivity;
import com.kscf.app.android.util.LxSPUtils;
import com.kscf.app.android.util.framing.LxRxBus;
import com.kscf.app.android.widget.LoadingPage;

/**
 * Created by luoyl on 2017/1/24.
 * 账户设置Fragment
 */

public class AccountSettingsFragment extends BaseFragment<FragmentAccountSettingsBinding, AccountSettingsFragmentPresenter> implements AccountSettingsFragmentContract.View, View.OnClickListener {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_account_settings;
    }


    public static AccountSettingsFragment newInstance() {
        return new AccountSettingsFragment();
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
        mDataBinding.rlItemHead.setOnClickListener(this);
        mDataBinding.btnConfirm.setOnClickListener(this);
        mDataBinding.itemContactPhone.setOnClickListener(this);
        mDataBinding.itemContactAddress.setOnClickListener(this);
        mDataBinding.itemLoginPassSetting.setOnClickListener(this);
        mDataBinding.itemTransactionPassSetting.setOnClickListener(this);

    }

    @Override
    public void initEventAndData() {

    }

    SparseArray<String> mFragmentNameArray = null;

    private String getFragmentClassName(int resId) {
        if (mFragmentNameArray == null) {
            mFragmentNameArray = new SparseArray<>(5);
            mFragmentNameArray.put(R.id.rl_item_head, MoreInformationFragment.class.getName());
            mFragmentNameArray.put(R.id.item_contact_phone, MyContactPhoneFragment.class.getName());
            mFragmentNameArray.put(R.id.item_contact_address, MyContactAddressFragment.class.getName());
            mFragmentNameArray.put(R.id.item_login_pass_setting, UpdateLoginPassFragment.class.getName());
            mFragmentNameArray.put(R.id.item_transaction_pass_setting, SettingsTransactionPassFragment.class.getName());
        }
        return mFragmentNameArray.get(resId);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_confirm:
                exitLogin();
                break;
            default:
                mActivity.addFragmentToActivity(mActivity, DetailsActivity.class, getFragmentClassName(v.getId()), true);
                break;


        }
    }

    /**
     * 退出登录
     */
    public void exitLogin() {
        LxSPUtils.putToken("");
        LxRxBus.getInstance().get().post(MainActivity.sRxBusLoginToHomeMyAccountTag, String.valueOf(0));
        mActivity.onBackPressedSupport();
    }


    @Override
    public void free() {
        if (mFragmentNameArray != null) {
            mFragmentNameArray.clear();
            mFragmentNameArray = null;
        }
        super.free();
    }


}
