package com.kscf.app.android.presenter.contract;

import com.kscf.app.android.base.BasePresenter;
import com.kscf.app.android.base.view.BaseView;

/**
 * 我的银行卡Fragment接口
 * Created by luoyl on 2017/2/7.
 */

public interface MyBankCardFragmentContract {

    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
    }
}
