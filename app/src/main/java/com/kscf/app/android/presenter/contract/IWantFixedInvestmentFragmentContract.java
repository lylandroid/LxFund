package com.kscf.app.android.presenter.contract;

import com.kscf.app.android.base.BasePresenter;
import com.kscf.app.android.base.view.BaseView;

/**
 * 我要定投Fragment接口
 * Created by luoyl on 2017/3/27.
 */

public interface IWantFixedInvestmentFragmentContract {

    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
    }
}
