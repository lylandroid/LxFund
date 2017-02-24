package com.kscf.app.android.presenter.contract;

import com.kscf.app.android.base.BasePresenter;
import com.kscf.app.android.base.view.BaseView;
import com.kscf.app.android.model.bean.HomeFundSelectedBannerBean;
import com.kscf.app.android.model.bean.LoginOrRegisterBean;

import java.util.List;
import java.util.Map;

/**
 * 风险测评Fragment接口
 * Created by luoyl on 2017/1/22.
 */

public interface RiskEvaluationFragmentContract {

    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
    }
}
