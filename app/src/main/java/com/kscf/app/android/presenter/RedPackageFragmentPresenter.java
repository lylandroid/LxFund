package com.kscf.app.android.presenter;

import com.framework.http.RetrofitHelper;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.presenter.contract.RedPackageFragmentContract;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/3/2.
 * 红包Presenter
 */

public class RedPackageFragmentPresenter extends RxPresenter<RedPackageFragmentContract.View> implements RedPackageFragmentContract.Presenter {

    @Inject
    public RedPackageFragmentPresenter() {
    }

    @Override
    public void start() {
    }

}
