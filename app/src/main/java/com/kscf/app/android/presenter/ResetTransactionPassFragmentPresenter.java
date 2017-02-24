package com.kscf.app.android.presenter;

import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.ResetTransactionPassFragmentContract;
import com.kscf.app.android.presenter.contract.UpdateTransactionPassFragmentContract;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/1/24.
 * 重置交易密码Presenter
 */

public class ResetTransactionPassFragmentPresenter extends RxPresenter<ResetTransactionPassFragmentContract.View> implements ResetTransactionPassFragmentContract.Presenter {

    @Inject
    public ResetTransactionPassFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
    }


}
