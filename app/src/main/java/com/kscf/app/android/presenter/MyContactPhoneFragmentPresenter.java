package com.kscf.app.android.presenter;

import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.MessageFragmentContract;
import com.kscf.app.android.presenter.contract.MyContactPhoneFragmentContract;

import javax.inject.Inject;

/**
 * 我的联系电话Presenter
 * Created by luoyl on 2017/3/6.
 */

public class MyContactPhoneFragmentPresenter extends RxPresenter<MyContactPhoneFragmentContract.View> implements MyContactPhoneFragmentContract.Presenter {

    @Inject
    public MyContactPhoneFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
    }

}
