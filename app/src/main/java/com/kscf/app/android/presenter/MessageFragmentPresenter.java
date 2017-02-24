package com.kscf.app.android.presenter;

import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.FundFragmentContract;
import com.kscf.app.android.presenter.contract.MessageFragmentContract;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/2/7.
 * 消息Presenter
 */

public class MessageFragmentPresenter extends RxPresenter<MessageFragmentContract.View> implements MessageFragmentContract.Presenter {

    @Inject
    public MessageFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
    }

}
