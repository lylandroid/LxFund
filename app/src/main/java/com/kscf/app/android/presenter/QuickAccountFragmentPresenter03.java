package com.kscf.app.android.presenter;

import com.framework.http.RetrofitHelper;
import com.kscf.app.android.base.RxPresenter;
import com.kscf.app.android.presenter.contract.QuickAccountContract03;

import javax.inject.Inject;

/**
 * Created by luoyl on 2017/2/10.
 * 快速开户Presenter
 */

public class QuickAccountFragmentPresenter03 extends RxPresenter<QuickAccountContract03.View> implements QuickAccountContract03.Presenter {

    @Inject
    public QuickAccountFragmentPresenter03() {
    }

    @Override
    public void start() {
    }

}
