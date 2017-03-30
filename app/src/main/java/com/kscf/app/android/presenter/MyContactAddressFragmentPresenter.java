package com.kscf.app.android.presenter;

import com.framework.http.RetrofitHelper;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.presenter.contract.MyContactAddressFragmentContract;

import javax.inject.Inject;

/**
 * 我的联系地址Presenter
 * Created by luoyl on 2017/3/9.
 */

public class MyContactAddressFragmentPresenter extends RxPresenter<MyContactAddressFragmentContract.View> implements MyContactAddressFragmentContract.Presenter {

    @Inject
    public MyContactAddressFragmentPresenter() {
    }

    @Override
    public void start() {
    }

}
