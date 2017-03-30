package com.kscf.app.android.presenter;

import com.framework.http.RetrofitHelper;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.presenter.contract.QuickAccountContract01;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/2/10.
 * 快速开户Presenter
 */

public class QuickAccountFragmentPresenter01 extends RxPresenter<QuickAccountContract01.View> implements QuickAccountContract01.Presenter {

    @Inject
    public QuickAccountFragmentPresenter01() {
    }

    @Override
    public void start() {
    }

}
