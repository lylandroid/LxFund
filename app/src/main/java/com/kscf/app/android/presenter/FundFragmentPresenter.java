package com.kscf.app.android.presenter;

import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.FundFragmentContract;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/2/6.
 * 基金Presenter
 */

public class FundFragmentPresenter extends RxPresenter<FundFragmentContract.View> implements FundFragmentContract.Presenter {

    @Inject
    public FundFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
    }

}
