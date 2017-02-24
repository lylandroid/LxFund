package com.kscf.app.android.presenter.contract;

import com.kscf.app.android.base.BasePresenter;
import com.kscf.app.android.base.view.BaseView;
import com.kscf.app.android.model.bean.BaseBean;
import com.kscf.app.android.model.bean.LoginOrRegisterBean;
import com.kscf.app.android.model.bean.OpenAccountStepBean;

import java.util.Map;

/**
 * 登录Fragment接口
 * Created by luoyl on 2016/12/26.
 */

public interface LoginFragmentContract {

    interface View extends BaseView {
        void onLoginSuccess(BaseBean<LoginOrRegisterBean> bean);

        void onGetCodeSuccess(BaseBean bannerBean);

        /*开户步骤回调api*/
        void onOpenAccountStepSuccess(BaseBean<OpenAccountStepBean> baseBean);
    }

    interface Presenter extends BasePresenter<View> {
        void loginOrRegister(Map<String, Object> param);

        void sendSmsCode(Map<String, Object> param);

        /**
         * 获取开户步骤
         */
        void getOpenAccountStep(Map<String,Object> param);
    }
}
