package com.kscf.app.android.presenter.contract;

import com.kscf.app.android.base.BasePresenter;
import com.kscf.app.android.base.view.BaseView;
import com.kscf.app.android.model.bean.HomeFundSelectedBannerBean;
import com.kscf.app.android.model.bean.LoginOrRegisterBean;

import java.util.List;
import java.util.Map;

/**
 * 基金Fragment接口
 * Created by luoyl on 2017/2/6.
 */

public interface FundFragmentContract {

    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
    }
}
