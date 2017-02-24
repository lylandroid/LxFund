package com.kscf.app.android.presenter;

import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.AccountSettingsFragmentContract;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/1/24.
 * 账户设置Presenter
 */

public class AccountSettingsFragmentPresenter extends RxPresenter<AccountSettingsFragmentContract.View> implements AccountSettingsFragmentContract.Presenter {

    @Inject
    public AccountSettingsFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
    }


}
