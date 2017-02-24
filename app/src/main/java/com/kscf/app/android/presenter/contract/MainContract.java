package com.kscf.app.android.presenter.contract;

import com.kscf.app.android.base.BasePresenter;
import com.kscf.app.android.base.view.BaseView;
import com.kscf.app.android.model.bean.MainBean;

/**
 * Created by luoyl on 2016/12/26.
 */

public interface MainContract {

    interface View extends BaseView {
        void showContent(MainBean o);

    }

    interface Presenter extends BasePresenter<View> {

    }
}
