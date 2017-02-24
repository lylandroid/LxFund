package com.kscf.app.android.presenter;

import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.AccountSettingsFragmentContract;
import com.kscf.app.android.presenter.contract.UpdateLoginPassFragmentContract;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/1/24.
 * 修改登录密码Presenter
 */

public class UpdateLoginPassFragmentPresenter extends RxPresenter<UpdateLoginPassFragmentContract.View> implements UpdateLoginPassFragmentContract.Presenter {

    @Inject
    public UpdateLoginPassFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
    }


}
