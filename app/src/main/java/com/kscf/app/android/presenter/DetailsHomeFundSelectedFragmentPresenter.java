package com.kscf.app.android.presenter;

import com.framework.http.RetrofitHelper;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.presenter.contract.MessageFragmentContract;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/2/7.
 * 消息Presenter
 */

public class DetailsHomeFundSelectedFragmentPresenter extends RxPresenter<MessageFragmentContract.View> implements MessageFragmentContract.Presenter {

    @Inject
    public DetailsHomeFundSelectedFragmentPresenter() {
    }

    @Override
    public void start() {
    }



}
