package com.kscf.app.android.presenter;

import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.MyContactAddressFragmentContract;

import javax.inject.Inject;

/**
 * 我的联系地址Presenter
 * Created by luoyl on 2017/3/9.
 */

public class MyContactAddressFragmentPresenter extends RxPresenter<MyContactAddressFragmentContract.View> implements MyContactAddressFragmentContract.Presenter {

    @Inject
    public MyContactAddressFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
    }

}
