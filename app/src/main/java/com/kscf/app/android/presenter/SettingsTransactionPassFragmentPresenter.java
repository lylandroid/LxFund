package com.kscf.app.android.presenter;

import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.SettingsTransastionPassFragmentContract;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/3/10.
 * 设置交易密码Presenter
 */

public class SettingsTransactionPassFragmentPresenter extends RxPresenter<SettingsTransastionPassFragmentContract.View> implements SettingsTransastionPassFragmentContract.Presenter {

    @Inject
    public SettingsTransactionPassFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
    }


}
