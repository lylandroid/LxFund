package com.kscf.app.android.presenter;

import com.framework.http.RetrofitHelper;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.presenter.contract.UpdateTransactionPassFragmentContract;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/1/24.
 * 修改交易密码Presenter
 */

public class UpdateTransactionPassFragmentPresenter extends RxPresenter<UpdateTransactionPassFragmentContract.View> implements UpdateTransactionPassFragmentContract.Presenter {

    @Inject
    public UpdateTransactionPassFragmentPresenter() {
    }

    @Override
    public void start() {
    }


}
