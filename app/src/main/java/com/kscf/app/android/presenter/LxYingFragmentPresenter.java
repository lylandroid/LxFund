package com.kscf.app.android.presenter;

import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.FundFragmentContract;
import com.kscf.app.android.presenter.contract.LxYingFragmentContract;

import javax.inject.Inject;

/**
 * 领先盈Presenter
 * Created by luoyl on 2017/3/2.
 */

public class LxYingFragmentPresenter extends RxPresenter<LxYingFragmentContract.View> implements LxYingFragmentContract.Presenter {

    @Inject
    public LxYingFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
    }

}
