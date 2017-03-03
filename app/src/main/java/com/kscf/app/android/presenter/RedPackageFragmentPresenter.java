package com.kscf.app.android.presenter;

import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.MessageFragmentContract;
import com.kscf.app.android.presenter.contract.RedPackageFragmentContract;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/3/2.
 * 红包Presenter
 */

public class RedPackageFragmentPresenter extends RxPresenter<RedPackageFragmentContract.View> implements RedPackageFragmentContract.Presenter {

    @Inject
    public RedPackageFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
    }

}
