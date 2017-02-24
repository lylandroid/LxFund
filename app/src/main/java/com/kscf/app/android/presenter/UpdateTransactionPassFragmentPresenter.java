package com.kscf.app.android.presenter;

import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.UpdateTransactionPassFragmentContract;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/1/24.
 * 修改交易密码Presenter
 */

public class UpdateTransactionPassFragmentPresenter extends RxPresenter<UpdateTransactionPassFragmentContract.View> implements UpdateTransactionPassFragmentContract.Presenter {

    @Inject
    public UpdateTransactionPassFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
    }


}
