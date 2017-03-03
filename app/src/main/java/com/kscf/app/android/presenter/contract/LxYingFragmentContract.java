package com.kscf.app.android.presenter.contract;

import com.kscf.app.android.base.BasePresenter;
import com.kscf.app.android.base.view.BaseView;

/**
 * 领先盈Fragment接口
 * Created by luoyl on 2017/3/2.
 */

public interface LxYingFragmentContract {

    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
    }
}
