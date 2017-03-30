package com.kscf.app.android.presenter;

import com.framework.http.RetrofitHelper;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.presenter.contract.MyBankCardFragmentContract;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/2/7.
 * 我的银行卡Presenter
 */

public class MyBankCardFragmentPresenter extends RxPresenter<MyBankCardFragmentContract.View> implements MyBankCardFragmentContract.Presenter {

    @Inject
    public MyBankCardFragmentPresenter() {
    }

    @Override
    public void start() {
    }

}
