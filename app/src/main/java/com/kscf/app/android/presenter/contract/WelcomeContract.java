package com.kscf.app.android.presenter.contract;

import com.kscf.app.android.base.BasePresenter;
import com.kscf.app.android.base.view.BaseView;
import com.kscf.app.android.model.bean.MainBean;

/**
 * Created by luoyl on 2016/12/26.
 * 欢迎页面接口
 */

public interface WelcomeContract {

    interface View extends BaseView {
        void showContent(MainBean o);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
