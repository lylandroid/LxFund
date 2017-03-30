package com.kscf.app.android.presenter;

import com.framework.http.RetrofitHelper;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.presenter.contract.MoreInformationFragmentContract;

import javax.inject.Inject;

/**
 * 更多信息Presenter
 * Created by luoyl on 2017/3/6.
 */

public class MoreInformationFragmentPresenter extends RxPresenter<MoreInformationFragmentContract.View> implements MoreInformationFragmentContract.Presenter {

    @Inject
    public MoreInformationFragmentPresenter() {
    }

    @Override
    public void start() {
    }

}
