package com.kscf.app.android.presenter.contract;

import com.kscf.app.android.base.BasePresenter;
import com.kscf.app.android.base.view.BaseView;

/**
 * 红包息Fragment接口
 * Created by luoyl on 2017/3/2.
 */

public interface RedPackageFragmentContract {

    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
    }
}
