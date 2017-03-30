package com.kscf.app.android.presenter;

import com.framework.http.RetrofitHelper;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.presenter.contract.QuickAccountContract02;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/2/10.
 * 快速开户Presenter
 */

public class QuickAccountFragmentPresenter02 extends RxPresenter<QuickAccountContract02.View> implements QuickAccountContract02.Presenter {

    @Inject
    public QuickAccountFragmentPresenter02() {
    }

    @Override
    public void start() {
    }

}
