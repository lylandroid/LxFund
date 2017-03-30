package com.kscf.app.android.presenter;

import com.framework.http.RetrofitHelper;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.presenter.contract.MyContactPhoneFragmentContract;

import javax.inject.Inject;

/**
 * 我的联系电话Presenter
 * Created by luoyl on 2017/3/6.
 */

public class MyContactPhoneFragmentPresenter extends RxPresenter<MyContactPhoneFragmentContract.View> implements MyContactPhoneFragmentContract.Presenter {

    @Inject
    public MyContactPhoneFragmentPresenter() {
    }

    @Override
    public void start() {
    }

}
