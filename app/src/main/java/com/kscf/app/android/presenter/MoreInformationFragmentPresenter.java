package com.kscf.app.android.presenter;

import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.model.http.RetrofitHelper;
import com.kscf.app.android.presenter.contract.MessageFragmentContract;
import com.kscf.app.android.presenter.contract.MoreInformationFragmentContract;

import javax.inject.Inject;

/**
 * 更多信息Presenter
 * Created by luoyl on 2017/3/6.
 */

public class MoreInformationFragmentPresenter extends RxPresenter<MoreInformationFragmentContract.View> implements MoreInformationFragmentContract.Presenter {

    @Inject
    public MoreInformationFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void start() {
    }

}
